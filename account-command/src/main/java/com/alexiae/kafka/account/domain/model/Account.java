package com.alexiae.kafka.account.domain.model;

import com.alexiae.kafka.account.domain.enums.AccountCurrency;
import com.alexiae.kafka.account.domain.enums.AccountStatus;
import com.alexiae.kafka.account.domain.enums.AccountType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private Long id;

    private String number;

    private String numberCci;

    private boolean isMain;

    private String holder;

    private AccountType type;

    private AccountCurrency currency;

    private BigDecimal currentBalance;

    private Date openingDate;

    private AccountStatus status;

    private BigDecimal dailyTransferLimit;

    private Date creationDate;

    private Date updateDate;

    private Long customerId;
}
