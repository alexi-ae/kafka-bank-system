package com.alexiae.kafka.customer.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentityInfo {

    private String documentCountry;
    private String documentType;
    private String documentNumber;
}
