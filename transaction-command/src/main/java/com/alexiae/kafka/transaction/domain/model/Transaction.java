package com.alexiae.kafka.transaction.domain.model;

import com.alexiae.kafka.transaction.domain.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Transaction {

    private Long id;

    private Long originAccountId;

    private Long destinationAccountId;

    private BigDecimal amount;

    private String description;

    private TransactionStatus status;

    private Date creationDate;

    private List<TransactionMovement> movements;
}
