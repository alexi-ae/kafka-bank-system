package com.alexi.kafka.customer.command.domain.port;

import com.alexi.kafka.customer.command.domain.model.Contact;

public interface ContactPersistencePort {

    Contact create(Contact customer);

}
