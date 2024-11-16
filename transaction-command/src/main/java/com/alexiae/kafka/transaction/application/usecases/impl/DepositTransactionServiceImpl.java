package com.alexiae.kafka.transaction.application.usecases.impl;

import com.alexiae.kafka.transaction.application.command.DepositTransactionResultCommand;
import com.alexiae.kafka.transaction.application.usecases.DepositTransactionService;
import com.alexiae.kafka.transaction.domain.enums.TransactionStatus;
import com.alexiae.kafka.transaction.domain.model.Transaction;
import com.alexiae.kafka.transaction.domain.port.TransactionPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DepositTransactionServiceImpl implements DepositTransactionService {

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
