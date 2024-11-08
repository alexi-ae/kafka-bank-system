package com.alexiae.kafka.customer.command.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OnbResponseDto {

    private String nextState;
    private String token;
}
