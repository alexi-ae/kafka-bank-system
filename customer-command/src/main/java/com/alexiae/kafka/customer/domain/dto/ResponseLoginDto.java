package com.alexiae.kafka.customer.domain.dto;

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
