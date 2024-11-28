package com.alexiae.kafka.customer.application.usecases;

import com.alexiae.kafka.customer.domain.enums.FileType;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.File;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import com.alexiae.kafka.customer.domain.port.in.ManageFileUseCase;
import com.alexiae.kafka.customer.domain.port.out.CustomerPersistencePort;
import com.alexiae.kafka.customer.domain.port.out.FilePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Service
public class ManageFileUseCaseImpl implements ManageFileUseCase {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private FilePersistencePort filePersistencePort;

    @Override
    public ObnInfo uploadObn(MultipartFile document, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        String urlUpload = generateUrlFromDocument(document);
        filePersistencePort.create(toFile(customer, urlUpload));
        customer.setNextState(OnboardingStatus.EXTRA_INFO);
        customerPersistencePort.updateNextState(customer.getId(), customer.getNextState().name());
        return ObnInfo.builder().nextState(customer.getNextState().name()).build();
    }


    File toFile(Customer customer, String urlUpload) {
        return File.builder().imagePath(urlUpload).type(FileType.IDENTIFICATION).customer(customer).build();
    }

    private String generateUrlFromDocument(MultipartFile document) {
        if (Objects.isNull(document)) {
            throw new RuntimeException("Document");
        }
        UUID uuid = UUID.randomUUID();
        String uuidText = uuid.toString();
        return "https:cloud-file/directory-/" + uuidText;
    }
}
