package com.alexiae.kafka.transaction.application.usecases;

import com.alexiae.kafka.transaction.domain.command.DepositTransactionResultCommand;
import com.alexiae.kafka.transaction.domain.port.in.DepositTransactionUseCase;
import com.alexiae.kafka.transaction.domain.enums.TransactionStatus;
import com.alexiae.kafka.transaction.domain.model.Transaction;
import com.alexiae.kafka.transaction.domain.port.out.TransactionPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepositTransactionServiceImpl implements DepositTransactionUseCase {

    @Autowired
    private TransactionPersistencePort transactionPersistencePort;

    @Override
    public void execute(DepositTransactionResultCommand command) {
        Transaction transaction = transactionPersistencePort.getById(command.getTransactionId());
        if(Objects.nonNull(transaction)){
            transaction.setStatus(TransactionStatus.getByKey(command.getStatus()));
            transactionPersistencePort.update(transaction);
        }
    }
}
