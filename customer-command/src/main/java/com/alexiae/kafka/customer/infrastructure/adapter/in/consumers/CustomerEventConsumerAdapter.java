package com.alexiae.kafka.customer.infrastructure.adapter.in.consumers;

import com.alexiae.kafka.customer.application.port.out.UserEventProducer;
import com.alexiae.kafka.customer.domain.enums.CustomerStatus;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.event.CreateCustomerEvent;
import com.alexiae.kafka.customer.domain.event.UpdateUserEvent;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.port.CustomerPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventConsumerAdapter {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private UserEventProducer userEventProducer;

    @KafkaListener(topics = "customer-create-topic", groupId = "customer-group-id",
            containerFactory = "customerCreateKafkaListenerFactory")
    public void create(CreateCustomerEvent event) {
        Customer customer = customerPersistencePort.create(Customer.builder()
                .userId(event.getUserId())
                .status(CustomerStatus.PENDING)
                .nextState(OnboardingStatus.CONTACT)
                .build());
        userEventProducer.publishUserUpdateEvent(UpdateUserEvent.builder()
                .userId(customer.getUserId())
                .customerId(customer.getId())
                .build());
    }
}
