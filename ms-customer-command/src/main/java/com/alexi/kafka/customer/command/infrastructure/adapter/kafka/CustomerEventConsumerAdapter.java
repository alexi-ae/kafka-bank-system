package com.alexi.kafka.customer.command.infrastructure.adapter.kafka;

import com.alexi.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import com.alexi.kafka.customer.command.domain.event.CreateCustomerEvent;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventConsumerAdapter {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @KafkaListener(topics = "customer-created-topic", groupId = "customer-group-id",
            containerFactory = "customerKafkaListenerFactory")
    public void create(CreateCustomerEvent event) {
        customerPersistencePort.create(Customer.builder()
                .userId(event.getUserId())
                .status(CustomerStatus.PENDING)
                .nextState(OnboardingStatus.CONTACT)
                .build());
    }
}
