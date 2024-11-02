package com.alexi.kafka.customer.command.domain.model;

import com.alexi.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;

    private OnboardingStatus nextState;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private CustomerStatus status;

    private Contact contact;

    private Document document;

    private List<Files> files;

    private ExtraInfo extraInfo;

    private User user;
}
