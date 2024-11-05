package com.alexiae.kafka.auth.domain.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseLoginDto {
    private String token;
}
