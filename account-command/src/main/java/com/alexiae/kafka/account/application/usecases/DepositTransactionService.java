package com.alexiae.kafka.account.application.usecases;

import com.alexiae.kafka.account.application.command.DepositTransactionCommand;

public interface DepositTransactionService {

    void execute(DepositTransactionCommand command);
}
