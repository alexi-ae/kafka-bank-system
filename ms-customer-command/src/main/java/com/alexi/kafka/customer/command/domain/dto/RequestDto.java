package com.alexi.kafka.customer.command.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private String jwttoken;

    private String nextStep;
}
