package com.alexiae.kafka.customer.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateExtraInfoRequest {

    private boolean politicallyExposed;
    private String maritalStatus;
    private String documentType;
    private String documentNumber;
    private String documentCountry;
}
