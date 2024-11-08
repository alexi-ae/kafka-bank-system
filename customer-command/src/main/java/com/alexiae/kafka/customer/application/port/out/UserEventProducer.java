package com.alexiae.kafka.customer.application.port.out;

import com.alexiae.kafka.customer.domain.event.UpdateUserEvent;

public interface UserEventProducer {
    void publishUserUpdateEvent(UpdateUserEvent event);

}
