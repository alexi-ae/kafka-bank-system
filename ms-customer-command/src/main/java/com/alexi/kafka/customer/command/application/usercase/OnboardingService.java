package com.alexi.kafka.customer.command.application.usercase;

import com.alexi.kafka.customer.command.application.command.ContactValidateCommand;
import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.application.command.CreatePersonalInfoCommand;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;

public interface OnboardingService {

    OnbResponseDto saveContact(CreateContactCommand request, long customerId);

    OnbResponseDto contactValidate(ContactValidateCommand request, long customerId);

    OnbResponseDto personalInfo(CreatePersonalInfoCommand request, long customerId);
}
