package com.alexiae.kafka.customer.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateIdentityInfoRequest {

    private String country;
    private String type;
    private String number;

}
