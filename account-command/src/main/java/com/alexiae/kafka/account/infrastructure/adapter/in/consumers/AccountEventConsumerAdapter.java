package com.alexiae.kafka.account.infrastructure.adapter.in.consumers;

import com.alexiae.kafka.account.application.command.CreateAccountCommand;
import com.alexiae.kafka.account.application.usecases.CreateAccountService;
import com.alexiae.kafka.account.domain.event.CreateAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class AccountEventConsumerAdapter {

    @Autowired
    private CreateAccountService createAccountService;

    @KafkaListener(topics = "account-create-topic", groupId = "customer-group-id",
            containerFactory = "accountCreateKafkaListenerFactory")
    public void create(CreateAccountEvent event) {

        createAccountService.execute(CreateAccountCommand.builder().customerId(event.getCustomerId())
                .firstName(event.getFirstName())
                .lastName(event.getLastName())
                .build());
    }
}
