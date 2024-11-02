package com.alexi.kafka.customer.command.application.service;

import com.alexi.kafka.customer.command.application.command.ContactValidateCommand;
import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.application.mapper.ContactMapper;
import com.alexi.kafka.customer.command.application.usercase.OnboardingService;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.port.ContactPersistencePort;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OnboardingManagementService implements OnboardingService {

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
        customer.setNextState(OnboardingStatus.CONTACT_VALIDATE);
        customer = customerPersistencePort.update(customer);
        return OnbResponseDto.builder().nextState(customer.getNextState().name()).build();
    }

    @SneakyThrows
    @Override
    public OnbResponseDto contactValidate(ContactValidateCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        Contact contact = customer.getContact();
        if (Objects.isNull(contact)) {
            throw new RuntimeException("Error");
        }

        if (validatePhoneNumber(contact, request.getCode())) {
            contact.setPhoneValidated(Boolean.TRUE);
        }
        contactPersistencePort.create(contact);
        customerPersistencePort.updateNextState(customer.getId(), OnboardingStatus.PERSONAL_INFO.name());
        return OnbResponseDto.builder().nextState(OnboardingStatus.PERSONAL_INFO.name()).build();
    }

    private boolean validatePhoneNumber(Contact contact, String code) {
        System.out.println("Validate contact");
        System.out.println("calling code: " + contact.getCallingCode());
        System.out.println("phone: " + contact.getPhoneNumber());
        System.out.println("code: " + code);
        return true;
    }
}
