package com.alexiae.kafka.customer.domain.port.in;

import com.alexiae.kafka.customer.domain.model.ObnInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ManageFileUseCase {

    ObnInfo uploadObn(MultipartFile document, long customerId);
}
