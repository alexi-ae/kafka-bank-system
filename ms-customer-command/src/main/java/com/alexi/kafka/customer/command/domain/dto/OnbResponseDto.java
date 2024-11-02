package com.alexi.kafka.customer.command.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OnbResponseDto {

    private String nextStep;
    private String token;
}
