package com.alexiae.kafka.account.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositTransactionCommand {

    private Long customerId;

    private Long originAccountId;

    private Long destinationAccountId;

    private BigDecimal amount;


}
