package com.alexi.kafka.customer.command.domain.port;

import com.alexi.kafka.customer.command.domain.model.Document;

public interface DocumentPersistencePort {

    Document create(Document document);
}
