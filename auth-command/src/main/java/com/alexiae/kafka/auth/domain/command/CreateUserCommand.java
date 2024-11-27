package com.alexiae.kafka.auth.domain.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserCommand {
    private String email;
    private String password;
}
