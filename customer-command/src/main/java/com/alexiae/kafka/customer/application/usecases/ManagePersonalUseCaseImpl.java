package com.alexiae.kafka.customer.application.usecases;

import com.alexiae.kafka.customer.application.mapper.ContactMapper;
import com.alexiae.kafka.customer.domain.command.CreatePersonalInfoCommand;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import com.alexiae.kafka.customer.domain.port.in.ManagePersonalUseCase;
import com.alexiae.kafka.customer.domain.port.out.CustomerPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagePersonalUseCaseImpl implements ManagePersonalUseCase {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ObnInfo save(CreatePersonalInfoCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer = contactMapper.fromPersonalInfo(customer, request);
        customer.setNextState(OnboardingStatus.IDENTITY_INFO);
        customer = customerPersistencePort.update(customer);
        return ObnInfo.builder().nextState(customer.getNextState().name()).build();
    }

}
