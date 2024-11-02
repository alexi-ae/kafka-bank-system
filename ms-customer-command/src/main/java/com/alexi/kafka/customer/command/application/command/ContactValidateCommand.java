package com.alexi.kafka.customer.command.application.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactValidateCommand {
    private String code;
}
