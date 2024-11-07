package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "extra_info")
public class ExtraInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "politically_exposed")
    private boolean politicallyExposed;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "document_country")
    private String documentCountry;
}
