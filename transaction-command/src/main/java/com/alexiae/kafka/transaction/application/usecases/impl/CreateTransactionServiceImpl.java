package com.alexiae.kafka.transaction.application.usecases.impl;

import com.alexiae.kafka.transaction.application.mapper.TransactionMapper;
import com.alexiae.kafka.transaction.application.usecases.CreateTransactionService;
import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedResponse;
import com.alexiae.kafka.transaction.domain.enums.MovementType;
import com.alexiae.kafka.transaction.domain.model.Transaction;
import com.alexiae.kafka.transaction.domain.model.TransactionMovement;
import com.alexiae.kafka.transaction.domain.port.TransactionMovementsPersistencePort;
import com.alexiae.kafka.transaction.domain.port.TransactionPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTransactionServiceImpl implements CreateTransactionService {

    @Autowired
    private TransactionPersistencePort transactionPersistencePort;

    @Autowired
    private TransactionMovementsPersistencePort transactionMovementsPersistencePort;

    @Autowired
    private TransactionMapper transactionMapper;

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
        return TransactionInitiatedResponse.builder().build();
    }
}
