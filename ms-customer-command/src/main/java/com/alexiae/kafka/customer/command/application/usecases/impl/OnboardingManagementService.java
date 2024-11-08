package com.alexiae.kafka.customer.command.application.usecases.impl;

import com.alexiae.kafka.customer.command.application.command.*;
import com.alexiae.kafka.customer.command.application.mapper.ContactMapper;
import com.alexiae.kafka.customer.command.application.mapper.DocumentMapper;
import com.alexiae.kafka.customer.command.application.mapper.ExtraInfoMapper;
import com.alexiae.kafka.customer.command.application.usecases.OnboardingService;
import com.alexiae.kafka.customer.command.domain.dto.OnbResponseDto;
import com.alexiae.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexiae.kafka.customer.command.domain.enums.FileType;
import com.alexiae.kafka.customer.command.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.command.domain.model.*;
import com.alexiae.kafka.customer.command.domain.port.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class OnboardingManagementService implements OnboardingService {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private ContactPersistencePort contactPersistencePort;

    @Autowired
    private DocumentPersistencePort documentPersistencePort;

    @Autowired
    private FilePersistencePort filePersistencePort;

    @Autowired
    private ExtraInfoPersistencePort extraInfoPersistencePort;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private ExtraInfoMapper extraInfoMapper;

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

    @Override
    public OnbResponseDto personalInfo(CreatePersonalInfoCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer = contactMapper.fromPersonalInfo(customer, request);
        customer.setNextState(OnboardingStatus.IDENTITY_INFO);
        customer = customerPersistencePort.update(customer);
        return OnbResponseDto.builder().nextState(customer.getNextState().name()).build();
    }

    @Override
    public OnbResponseDto identityInfo(CreateIdentityInfoCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setDocument(Objects.isNull(customer.getDocument()) ? new Document() : customer.getDocument());

        Document document = documentMapper.toModel(customer.getDocument(), request);
        document = documentPersistencePort.create(document);

        customer.setDocument(document);
        customer.setNextState(OnboardingStatus.UPLOAD_DOCUMENT);
        customer = customerPersistencePort.update(customer);
        return OnbResponseDto.builder().nextState(customer.getNextState().name()).build();
    }

    @Override
    public OnbResponseDto uploadDocument(MultipartFile document, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        String urlUpload = generateUrlFromDocument(document);
        filePersistencePort.create(toFile(customer, urlUpload));
        customer.setNextState(OnboardingStatus.EXTRA_INFO);
        customerPersistencePort.updateNextState(customer.getId(), customer.getNextState().name());
        return OnbResponseDto.builder().nextState(customer.getNextState().name()).build();
    }

    @Override
    public OnbResponseDto extraInfo(CreateExtraInfoCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setExtraInfo(Objects.isNull(customer.getExtraInfo()) ? new ExtraInfo() : customer.getExtraInfo());

        ExtraInfo extraInfo = extraInfoMapper.toModel(customer.getExtraInfo(), request);
        extraInfo = extraInfoPersistencePort.create(extraInfo);

        customer.setExtraInfo(extraInfo);
        customer.setNextState(OnboardingStatus.PROCESSING);
        customer = customerPersistencePort.update(customer);
        return OnbResponseDto.builder().nextState(customer.getNextState().name()).build();
    }

    File toFile(Customer customer, String urlUpload) {
        return File.builder().imagePath(urlUpload).type(FileType.IDENTIFICATION).customer(customer).build();
    }

    @Override
    public OnbResponseDto processingInfo(long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setNextState(OnboardingStatus.HOME);
        customer.setStatus(CustomerStatus.APPROVED);
        customer = customerPersistencePort.update(customer);
        return OnbResponseDto.builder().nextState(customer.getNextState().name()).build();
    }

    private String generateUrlFromDocument(MultipartFile document) {
        if (Objects.isNull(document)) {
            throw new RuntimeException("Document");
        }
        UUID uuid = UUID.randomUUID();
        String uuidText = uuid.toString();
        return "https:cloud-file/directory-/" + uuidText;
    }

    private boolean validatePhoneNumber(Contact contact, String code) {
        System.out.println("Validate contact");
        System.out.println("calling code: " + contact.getCallingCode());
        System.out.println("phone: " + contact.getPhoneNumber());
        System.out.println("code: " + code);
        return true;
    }
}
