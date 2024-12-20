package com.alexiae.kafka.transaction.application.usecases;

import com.alexiae.kafka.transaction.application.mapper.TransactionMapper;
import com.alexiae.kafka.transaction.application.port.out.TransactionEventProducer;
import com.alexiae.kafka.transaction.domain.port.in.CreateTransactionUseCase;
import com.alexiae.kafka.transaction.application.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.application.dto.TransactionInitiatedResponse;
import com.alexiae.kafka.transaction.domain.enums.MovementType;
import com.alexiae.kafka.transaction.domain.event.DepositTransactionEvent;
import com.alexiae.kafka.transaction.domain.model.Transaction;
import com.alexiae.kafka.transaction.domain.model.TransactionMovement;
import com.alexiae.kafka.transaction.domain.port.out.TransactionMovementsPersistencePort;
import com.alexiae.kafka.transaction.domain.port.out.TransactionPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionServiceImpl implements CreateTransactionUseCase {

    @Autowired
    private TransactionPersistencePort transactionPersistencePort;

    @Autowired
    private TransactionMovementsPersistencePort transactionMovementsPersistencePort;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionEventProducer transactionEventProducer;

    @Override
    public TransactionInitiatedResponse init(TransactionInitiatedRequest request, Long customerId) {

        // crear transaccion
        Transaction transaction = transactionPersistencePort.create(transactionMapper.toModel(request));
        // crear movimiento
        TransactionMovement origin = TransactionMovement.builder()
                .accountId(transaction.getOriginAccountId())
                .amount(transaction.getAmount().negate())
                .transaction(transaction)
                .movementType(MovementType.SENT)
                .build();
        transactionMovementsPersistencePort.create(origin);
        TransactionMovement destiny = TransactionMovement.builder()
                .accountId(transaction.getDestinationAccountId())
                .amount(transaction.getAmount())
                .transaction(transaction)
                .movementType(MovementType.RECEIVED)
                .build();
        transactionMovementsPersistencePort.create(destiny);

        transactionEventProducer.publishTransactionDepositEvent(DepositTransactionEvent.builder()
                .transactionId(transaction.getId())
                .originAccountId(transaction.getOriginAccountId())
                .destinationAccountId(transaction.getDestinationAccountId())
                .amount(transaction.getAmount())
                .customerId(customerId)
                .build());

        return TransactionInitiatedResponse.builder()
                .transactionId(transaction.getId()).status("PENDING").build();
    }
}
