package com.alexi.kafka.customer.command.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginDto {
    private String jwttoken;

    private String nextState;
}
