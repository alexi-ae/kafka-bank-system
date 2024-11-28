package com.alexiae.kafka.customer.domain.port.out;

import com.alexiae.kafka.customer.domain.model.Document;

public interface DocumentPersistencePort {

    Document create(Document document);
}
