package com.alexiae.kafka.customer.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private Long id;

    private String countryCode;

    private String callingCode;

    private String phoneNumber;

    private boolean isPhoneValidated;
}
