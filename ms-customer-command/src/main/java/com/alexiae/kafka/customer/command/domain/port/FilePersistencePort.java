package com.alexiae.kafka.customer.command.domain.port;

import com.alexiae.kafka.customer.command.domain.model.File;

public interface FilePersistencePort {

    File create(File files);
}
