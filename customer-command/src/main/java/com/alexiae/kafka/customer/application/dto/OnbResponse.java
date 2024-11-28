package com.alexiae.kafka.customer.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OnbResponse {

    private String nextState;
    private String token;
}
