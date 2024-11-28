package com.alexiae.kafka.customer.domain.port.in;

import com.alexiae.kafka.customer.domain.command.ContactValidateCommand;
import com.alexiae.kafka.customer.domain.command.CreateContactCommand;
import com.alexiae.kafka.customer.domain.model.ObnInfo;

public interface ManageContactUseCase {

    ObnInfo save(CreateContactCommand request, long customerId);

    ObnInfo validate(ContactValidateCommand request, long customerId);
}
