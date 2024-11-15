package com.alexiae.kafka.transaction.application.usecases;

import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedResponse;

public interface CreateTransactionService {
    TransactionInitiatedResponse init(TransactionInitiatedRequest request, Long customerId);
}
