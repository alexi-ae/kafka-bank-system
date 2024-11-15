package com.alexiae.kafka.transaction.domain.model;

import com.alexiae.kafka.transaction.domain.enums.MovementType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class TransactionMovement {

    private Long id;

    private Transaction transaction;

    private Long accountId;

    private BigDecimal amount;

    private MovementType movementType;

}
