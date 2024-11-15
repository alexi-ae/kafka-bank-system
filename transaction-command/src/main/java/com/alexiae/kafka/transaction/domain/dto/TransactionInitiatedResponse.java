package com.alexiae.kafka.transaction.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TransactionInitiatedResponse {

    private Long transactionId;
    private Long status;

    private Long movementOrigin;
    private Long movementDestiny;

}