package com.alexiae.kafka.auth.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String id;
    private String email;
}
