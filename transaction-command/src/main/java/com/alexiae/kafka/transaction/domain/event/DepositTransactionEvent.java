package com.alexiae.kafka.transaction.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTransactionEvent {

    private Long customerId;

    private Long originAccountId;

    private Long destinationAccountId;

    private BigDecimal amount;


}
