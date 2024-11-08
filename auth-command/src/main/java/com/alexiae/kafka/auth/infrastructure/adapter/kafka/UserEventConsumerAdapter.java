package com.alexiae.kafka.auth.infrastructure.adapter.kafka;

import com.alexiae.kafka.auth.domain.event.UpdateUserEvent;
import com.alexiae.kafka.auth.domain.port.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventConsumerAdapter {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @KafkaListener(topics = "user-update-topic", groupId = "customer-group-id",
            containerFactory = "userUpdateKafkaListenerFactory")
    public void create(UpdateUserEvent event) {
        userPersistencePort.updateCustomerId(event.getUserId(), event.getCustomerId());
    }
}
