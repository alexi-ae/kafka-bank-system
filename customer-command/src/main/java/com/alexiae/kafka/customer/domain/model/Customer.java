package com.alexiae.kafka.customer.domain.model;

import com.alexiae.kafka.customer.domain.enums.CustomerStatus;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
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

    private IdentityInfo identityInfo;

    private Document document;

    private List<File> files;

    private ExtraInfo extraInfo;

    private String userId;

}
