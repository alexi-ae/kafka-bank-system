package com.alexiae.kafka.account.domain.port.in;

import com.alexiae.kafka.account.domain.command.DepositTransactionCommand;

public interface DepositTransactionUseCase {

    void execute(DepositTransactionCommand command);
}
