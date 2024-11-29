package com.alexiae.kafka.transaction.domain.port.out;

import com.alexiae.kafka.transaction.domain.model.TransactionMovement;

public interface TransactionMovementsPersistencePort {
    TransactionMovement create(TransactionMovement model);
}
