package com.alexiae.kafka.transaction.domain.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTransactionResultCommand {

    private Long transactionId;
    private String status;
}
