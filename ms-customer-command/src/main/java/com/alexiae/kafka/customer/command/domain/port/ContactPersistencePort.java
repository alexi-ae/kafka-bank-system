package com.alexiae.kafka.customer.command.domain.port;

import com.alexiae.kafka.customer.command.domain.model.Contact;

public interface ContactPersistencePort {

    Contact create(Contact customer);

}
