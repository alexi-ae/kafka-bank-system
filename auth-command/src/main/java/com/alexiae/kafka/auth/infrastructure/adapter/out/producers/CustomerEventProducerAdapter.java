package com.alexiae.kafka.auth.infrastructure.adapter.out.producers;

import com.alexiae.kafka.auth.application.port.out.CustomerEventPublisher;
import com.alexiae.kafka.auth.domain.event.CreateCustomerEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventProducerAdapter implements CustomerEventPublisher {

    @Value("${spring.kafka.producer.topic.customer-create}")
    private String customerCreateTopic;

    @Autowired
    private KafkaTemplate<String, CreateCustomerEvent> createCustomerEventKafkaTemplate;

    @Override
    public void publishCustomerCreatedEvent(CreateCustomerEvent event) {
        createCustomerEventKafkaTemplate.send(customerCreateTopic, event);
    }
}
