package com.alexiae.kafka.transaction.application.usecases;


import com.alexiae.kafka.transaction.application.command.DepositTransactionResultCommand;

public interface DepositTransactionService {

    void execute(DepositTransactionResultCommand command);
}
