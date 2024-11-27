package com.alexiae.kafka.auth.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String id;
    private String email;
}
