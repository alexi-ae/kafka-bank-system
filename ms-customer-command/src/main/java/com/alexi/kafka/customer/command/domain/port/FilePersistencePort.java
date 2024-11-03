package com.alexi.kafka.customer.command.domain.port;

import com.alexi.kafka.customer.command.domain.model.File;

public interface FilePersistencePort {

    File create(File files);
}
