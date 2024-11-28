package com.alexiae.kafka.customer.domain.port.out;

import com.alexiae.kafka.customer.domain.model.File;

public interface FilePersistencePort {

    File create(File files);
}
