package com.alexiae.kafka.customer.domain.port.in;

import com.alexiae.kafka.customer.domain.command.CreateExtraInfoCommand;
import com.alexiae.kafka.customer.domain.model.ObnInfo;

public interface ManageExtraDataUseCase {

    ObnInfo save(CreateExtraInfoCommand request, long customerId);
}
