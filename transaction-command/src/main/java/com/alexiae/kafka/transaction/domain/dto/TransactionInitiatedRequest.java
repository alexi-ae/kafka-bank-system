package com.alexiae.kafka.transaction.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TransactionInitiatedRequest {

    private String originAccount;
    private String destinationAccount;
    private Long originAccountId;
    private Long destinationAccountId;
    private BigDecimal amount;
    private String description;

}
