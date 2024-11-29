package com.alexiae.kafka.transaction.domain.port.in;


import com.alexiae.kafka.transaction.domain.command.DepositTransactionResultCommand;

public interface DepositTransactionUseCase {

    void execute(DepositTransactionResultCommand command);
}
