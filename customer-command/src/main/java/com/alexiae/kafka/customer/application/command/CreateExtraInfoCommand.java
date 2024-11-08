package com.alexiae.kafka.customer.application.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExtraInfoCommand {

    private boolean politicallyExposed;
    private String maritalStatus;
    private String documentType;
    private String documentNumber;
    private String documentCountry;
}
