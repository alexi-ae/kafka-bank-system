package com.alexiae.kafka.customer.application.usecases;

import com.alexiae.kafka.customer.application.mapper.ContactMapper;
import com.alexiae.kafka.customer.domain.command.ContactValidateCommand;
import com.alexiae.kafka.customer.domain.command.CreateContactCommand;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.model.Contact;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import com.alexiae.kafka.customer.domain.port.in.ManageContactUseCase;
import com.alexiae.kafka.customer.domain.port.out.ContactPersistencePort;
import com.alexiae.kafka.customer.domain.port.out.CustomerPersistencePort;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ManageContactUseCaseImpl implements ManageContactUseCase {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private ContactPersistencePort contactPersistencePort;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public ObnInfo save(CreateContactCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setContact(Objects.isNull(customer.getContact()) ? new Contact() : customer.getContact());

        Contact contact = contactMapper.toModel(customer.getContact(), request);
        contact = contactPersistencePort.create(contact);

        customer.setContact(contact);
        customer.setNextState(OnboardingStatus.CONTACT_VALIDATE);
        customer = customerPersistencePort.update(customer);
        return ObnInfo.builder().nextState(customer.getNextState().name()).build();
    }

    @SneakyThrows
    @Override
    public ObnInfo validate(ContactValidateCommand request, long customerId) {
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
        return ObnInfo.builder().nextState(OnboardingStatus.PERSONAL_INFO.name()).build();
    }


    private boolean validatePhoneNumber(Contact contact, String code) {
        System.out.println("Validate contact");
        System.out.println("calling code: " + contact.getCallingCode());
        System.out.println("phone: " + contact.getPhoneNumber());
        System.out.println("code: " + code);
        return true;
    }
}
