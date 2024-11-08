package com.alexi.kafka.customer.command.infrastructure.adapter.kafka;

import com.alexi.kafka.customer.command.adapters.out.UserEventPublisher;
import com.alexi.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import com.alexi.kafka.customer.command.domain.event.CreateCustomerEvent;
import com.alexi.kafka.customer.command.domain.event.UpdateUserEvent;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventConsumerAdapter {

    @Value("${spring.kafka.consumer.topic.customer-create}")
    private String customerCreateTopic;

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private UserEventPublisher userEventPublisher;

    @KafkaListener(topics = "customer-created-topic", groupId = "customer-group-id",
            containerFactory = "customerCreateKafkaListenerFactory")
    public void create(CreateCustomerEvent event) {
        Customer customer = customerPersistencePort.create(Customer.builder()
                .userId(event.getUserId())
                .status(CustomerStatus.PENDING)
                .nextState(OnboardingStatus.CONTACT)
                .build());
        userEventPublisher.publishUserUpdateEvent(UpdateUserEvent.builder()
                .userId(customer.getUserId())
                .customerId(customer.getId())
                .build());
    }
}
