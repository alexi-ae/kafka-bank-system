package com.alexiae.kafka.customer.domain.port;

import com.alexiae.kafka.customer.domain.model.Contact;

public interface ContactPersistencePort {

    Contact create(Contact customer);

}
