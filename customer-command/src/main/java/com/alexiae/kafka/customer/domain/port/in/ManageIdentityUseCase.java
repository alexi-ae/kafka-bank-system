package com.alexiae.kafka.customer.domain.port.in;

import com.alexiae.kafka.customer.domain.command.CreateIdentityInfoCommand;
import com.alexiae.kafka.customer.domain.model.ObnInfo;

public interface ManageIdentityUseCase {

    ObnInfo save(CreateIdentityInfoCommand request, long customerId);
}
