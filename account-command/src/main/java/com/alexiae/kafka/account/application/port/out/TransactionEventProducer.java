package com.alexiae.kafka.account.application.port.out;

import com.alexiae.kafka.account.domain.event.DepositTransactionResultEvent;

public interface TransactionEventProducer {
    void publishTransactionDepositResultEvent(DepositTransactionResultEvent event);

}
