package com.alexi.kafka.customer.command.application.port.out;

import com.alexi.kafka.customer.command.domain.event.UpdateUserEvent;

public interface UserEventProducer {
    void publishUserUpdateEvent(UpdateUserEvent event);

}
