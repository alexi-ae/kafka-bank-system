package com.alexi.kafka.customer.command.application.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIdentityInfoCommand {

    private String country;
    private String type;
    private String number;

}
