package com.alexiae.kafka.transaction.application.port.out;

import com.alexiae.kafka.transaction.domain.event.DepositTransactionEvent;

public interface TransactionEventProducer {
    void publishTransactionDepositEvent(DepositTransactionEvent event);

}
