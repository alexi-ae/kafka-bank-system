package com.alexiae.kafka.auth.application.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    private String email;
    private String password;
}
