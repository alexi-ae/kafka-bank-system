package com.alexiae.kafka.customer.command.domain.port;

import com.alexiae.kafka.customer.command.domain.model.Document;

public interface DocumentPersistencePort {

    Document create(Document document);
}
