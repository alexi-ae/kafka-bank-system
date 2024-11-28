package com.alexiae.kafka.customer.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ObnInfo {
    private String nextState;
    private String token;
}
