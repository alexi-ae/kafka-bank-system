package com.alexiae.kafka.customer.domain.port;

import com.alexiae.kafka.customer.domain.model.Document;

public interface DocumentPersistencePort {

    Document create(Document document);
}
