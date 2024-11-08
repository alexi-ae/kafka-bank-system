package com.alexiae.kafka.customer.application.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {
    private String email;
    private String password;
}
