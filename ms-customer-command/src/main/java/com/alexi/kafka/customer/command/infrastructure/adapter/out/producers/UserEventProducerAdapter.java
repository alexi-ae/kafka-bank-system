package com.alexi.kafka.customer.command.infrastructure.adapter.out.producers;

import com.alexi.kafka.customer.command.application.port.out.UserEventProducer;
import com.alexi.kafka.customer.command.domain.event.UpdateUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducerAdapter implements UserEventProducer {

    @Value("${spring.kafka.producer.topic.user-update}")
    private String userUpdateTopic;

    @Autowired
    private KafkaTemplate<String, UpdateUserEvent> updateUserEventKafkaTemplate;

    @Override
    public void publishUserUpdateEvent(UpdateUserEvent event) {
        updateUserEventKafkaTemplate.send(userUpdateTopic, event);
    }
}