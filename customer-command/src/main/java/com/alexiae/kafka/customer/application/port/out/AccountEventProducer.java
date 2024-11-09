package com.alexiae.kafka.customer.application.port.out;

import com.alexiae.kafka.customer.domain.event.CreateAccountEvent;

public interface AccountEventProducer {
    void produceAccountCreateEvent(CreateAccountEvent event);

}
