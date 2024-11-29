package com.alexiae.kafka.transaction.domain.port.in;

import com.alexiae.kafka.transaction.application.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.application.dto.TransactionInitiatedResponse;

public interface CreateTransactionUseCase {
    TransactionInitiatedResponse init(TransactionInitiatedRequest request, Long customerId);
}
