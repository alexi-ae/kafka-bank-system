package com.alexiae.kafka.customer.command.domain.dto;

import com.alexiae.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexiae.kafka.customer.command.domain.enums.OnboardingStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CustomerDto {
    private Long id;

    private OnboardingStatus nextState;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private CustomerStatus status;
}
