package com.alexiae.kafka.account.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAccountEvent {
    private Long customerId;
    private String firstName;
    private String lastName;
}
