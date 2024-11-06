package com.alexiae.kafka.auth.application.port.out;

import com.alexiae.kafka.auth.domain.event.CreateCustomerEvent;

public interface CustomerEventPublisher {
    void publishCustomerCreatedEvent(CreateCustomerEvent event);
}
