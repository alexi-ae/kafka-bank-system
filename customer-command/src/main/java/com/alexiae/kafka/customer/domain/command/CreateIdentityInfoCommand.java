package com.alexiae.kafka.customer.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIdentityInfoCommand {

    private String country;
    private String type;
    private String number;

}
