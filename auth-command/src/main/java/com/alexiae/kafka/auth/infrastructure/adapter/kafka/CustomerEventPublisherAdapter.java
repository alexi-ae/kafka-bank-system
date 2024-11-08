package com.alexiae.kafka.auth.infrastructure.adapter.kafka;

import com.alexiae.kafka.auth.application.port.out.CustomerEventPublisher;
import com.alexiae.kafka.auth.domain.event.CreateCustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventPublisherAdapter implements CustomerEventPublisher {

    @Value("${spring.kafka.producer.customer.create}")
    private String customerCreate;

    @Autowired
    private KafkaTemplate<String, CreateCustomerEvent> createCustomerEventKafkaTemplate;

    @Override
    public void publishCustomerCreatedEvent(CreateCustomerEvent event) {
        createCustomerEventKafkaTemplate.send(customerCreate, event);
    }
}
