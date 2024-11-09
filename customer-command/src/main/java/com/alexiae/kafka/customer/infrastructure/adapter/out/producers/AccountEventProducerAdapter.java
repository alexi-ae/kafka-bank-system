package com.alexiae.kafka.customer.infrastructure.adapter.out.producers;

import com.alexiae.kafka.customer.application.port.out.AccountEventProducer;
import com.alexiae.kafka.customer.domain.event.CreateAccountEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountEventProducerAdapter implements AccountEventProducer {

    @Value("${spring.kafka.producer.topic.account-create}")
    private String accountCreateTopic;

    @Autowired
    private KafkaTemplate<String, CreateAccountEvent> createAccountEventKafkaTemplate;

    @Override
    public void produceAccountCreateEvent(CreateAccountEvent event) {
        createAccountEventKafkaTemplate.send(accountCreateTopic, event);
    }
}
