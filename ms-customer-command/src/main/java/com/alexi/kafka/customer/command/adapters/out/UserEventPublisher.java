package com.alexi.kafka.customer.command.adapters.out;

import com.alexi.kafka.customer.command.domain.event.UpdateUserEvent;

public interface UserEventPublisher {
    void publishUserUpdateEvent(UpdateUserEvent event);

}
