package com.alexiae.kafka.customer.application.usecases;

import com.alexiae.kafka.customer.application.mapper.DocumentMapper;
import com.alexiae.kafka.customer.domain.command.CreateIdentityInfoCommand;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.Document;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import com.alexiae.kafka.customer.domain.port.in.ManageIdentityUseCase;
import com.alexiae.kafka.customer.domain.port.out.CustomerPersistencePort;
import com.alexiae.kafka.customer.domain.port.out.DocumentPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ManageIdentityUseCaseImpl implements ManageIdentityUseCase {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private DocumentPersistencePort documentPersistencePort;

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public ObnInfo save(CreateIdentityInfoCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setDocument(Objects.isNull(customer.getDocument()) ? new Document() : customer.getDocument());

        Document document = documentMapper.toModel(customer.getDocument(), request);
        document = documentPersistencePort.create(document);

        customer.setDocument(document);
        customer.setNextState(OnboardingStatus.UPLOAD_DOCUMENT);
        customer = customerPersistencePort.update(customer);
        return ObnInfo.builder().nextState(customer.getNextState().name()).build();
    }
}
