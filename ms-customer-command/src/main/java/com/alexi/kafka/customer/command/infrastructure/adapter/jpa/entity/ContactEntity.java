package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "calling_code")
    private String callingCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "phone_validated")
    private boolean isPhoneValidated;
}
