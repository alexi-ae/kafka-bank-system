package com.alexiae.kafka.transaction.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTransactionResultEvent {

    private Long transactionId;

    private String status;
}
