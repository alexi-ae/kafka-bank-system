package com.alexiae.kafka.customer.domain.port.in;

import com.alexiae.kafka.customer.domain.command.CreatePersonalInfoCommand;
import com.alexiae.kafka.customer.domain.model.ObnInfo;

public interface ManagePersonalUseCase {

    ObnInfo save(CreatePersonalInfoCommand request, long customerId);
}
