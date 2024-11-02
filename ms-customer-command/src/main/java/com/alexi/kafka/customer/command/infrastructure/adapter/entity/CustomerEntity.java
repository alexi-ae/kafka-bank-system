package com.alexi.kafka.customer.command.infrastructure.adapter.entity;

import com.alexi.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "next_state")
    private OnboardingStatus nextState;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CustomerStatus status;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ContactEntity contact;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
