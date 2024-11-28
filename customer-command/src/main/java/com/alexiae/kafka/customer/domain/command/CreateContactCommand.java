package com.alexiae.kafka.customer.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateContactCommand {

    private String countryCode;
    private String callingCode;
    private String phoneNumber;
}
