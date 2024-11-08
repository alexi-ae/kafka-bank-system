package com.alexiae.kafka.customer.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private String jwttoken;

    private String nextState;
}
