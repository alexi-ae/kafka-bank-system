package com.alexi.kafka.customer.command.application.usercase;

import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;

public interface CustomerService {

    OnbResponseDto saveContact(CreateContactCommand request, long customerId);
}
