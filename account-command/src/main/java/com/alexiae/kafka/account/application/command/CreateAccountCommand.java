package com.alexiae.kafka.account.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountCommand {
    private Long customerId;
    private String firstName;
    private String lastName;
}
