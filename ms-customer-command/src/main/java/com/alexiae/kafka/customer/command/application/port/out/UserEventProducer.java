package com.alexiae.kafka.customer.command.application.port.out;

import com.alexiae.kafka.customer.command.domain.event.UpdateUserEvent;

public interface UserEventProducer {
    void publishUserUpdateEvent(UpdateUserEvent event);

}
