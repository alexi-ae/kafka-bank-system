package com.alexiae.kafka.customer.application.usecases;

import com.alexiae.kafka.customer.application.port.out.AccountEventProducer;
import com.alexiae.kafka.customer.domain.enums.CustomerStatus;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.event.CreateAccountEvent;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import com.alexiae.kafka.customer.domain.port.in.OnboardingUseCase;
import com.alexiae.kafka.customer.domain.port.out.CustomerPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingUseCaseImpl implements OnboardingUseCase {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private AccountEventProducer accountEventProducer;

    @Override
    public ObnInfo process(long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setNextState(OnboardingStatus.HOME);
        customer.setStatus(CustomerStatus.APPROVED);
        customer = customerPersistencePort.update(customer);
        accountEventProducer.produceAccountCreateEvent(CreateAccountEvent.builder()
                .customerId(customerId)
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build());
        return ObnInfo.builder().nextState(customer.getNextState().name()).build();
    }
}
