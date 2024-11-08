package com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.entity;

import com.alexiae.kafka.account.domain.enums.AccountCurrency;
import com.alexiae.kafka.account.domain.enums.AccountStatus;
import com.alexiae.kafka.account.domain.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, unique = true)
    private String number;

    @Column(name = "number_cci", nullable = false, unique = true)
    private String numberCci;

    @Column(name = "is_main")
    private boolean isMain;

    @Column(name = "holder", nullable = false)
    private String holder;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private AccountCurrency currency;

    @Column(name = "current_balance", nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "opening_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date openingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status;

    @Column(name = "daily_transfer_limit")
    private BigDecimal dailyTransferLimit;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;


    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = new Date();
    }

    @Column(name = "customer_id")
    private Long customerId;
}
