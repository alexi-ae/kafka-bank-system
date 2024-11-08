package com.alexiae.kafka.customer.command.application.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactValidateCommand {
    private String code;
}
