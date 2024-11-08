package com.alexi.kafka.customer.command.infrastructure.adapter.in.consumers;

import com.alexi.kafka.customer.command.application.port.out.UserEventProducer;
import com.alexi.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import com.alexi.kafka.customer.command.domain.event.CreateCustomerEvent;
import com.alexi.kafka.customer.command.domain.event.UpdateUserEvent;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
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
