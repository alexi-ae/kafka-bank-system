package com.alexi.kafka.customer.command.application.service;

import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.application.mapper.ContactMapper;
import com.alexi.kafka.customer.command.application.usercase.CustomerService;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;
import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.port.ContactPersistencePort;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerManagementService implements CustomerService {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private ContactPersistencePort contactPersistencePort;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public OnbResponseDto saveContact(CreateContactCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setContact(Objects.isNull(customer.getContact()) ? new Contact() : customer.getContact());
        Contact contact = contactMapper.toModel(customer.getContact(), request);

        contact = contactPersistencePort.create(contact);
        customer.setContact(contact);
        customer = customerPersistencePort.update(customer);
        return OnbResponseDto.builder().nextStep(customer.getNextState().name()).build();
    }
}
