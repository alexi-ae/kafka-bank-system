package com.alexiae.kafka.customer.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactRequest {

    private String countryCode;
    private String callingCode;
    private String phoneNumber;
}
