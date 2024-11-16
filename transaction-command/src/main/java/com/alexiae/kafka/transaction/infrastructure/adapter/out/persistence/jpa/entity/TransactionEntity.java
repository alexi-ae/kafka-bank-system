package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity;

import com.alexiae.kafka.transaction.domain.enums.TransactionStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "origin_account_id")
    private String originAccountId;

    @Column(name = "destination_account_id")
    private String destinationAccountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;


    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
    }


    @OneToMany(mappedBy = "transaction", cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<TransactionMovementEntity> movements = new ArrayList<>();

}
