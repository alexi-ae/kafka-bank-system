package com.alexiae.kafka.transaction.domain.port;

import com.alexiae.kafka.transaction.domain.model.Transaction;

public interface TransactionPersistencePort {
    Transaction create(Transaction model);
}
