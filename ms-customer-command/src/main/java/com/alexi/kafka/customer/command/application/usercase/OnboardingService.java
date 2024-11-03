package com.alexi.kafka.customer.command.application.usercase;

import com.alexi.kafka.customer.command.application.command.*;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface OnboardingService {

    OnbResponseDto saveContact(CreateContactCommand request, long customerId);

    OnbResponseDto contactValidate(ContactValidateCommand request, long customerId);

    OnbResponseDto personalInfo(CreatePersonalInfoCommand request, long customerId);

    OnbResponseDto identityInfo(CreateIdentityInfoCommand request, long customerId);

    OnbResponseDto uploadDocument(MultipartFile document, long customerId);

    OnbResponseDto extraInfo(CreateExtraInfoCommand request, long customerId);

    OnbResponseDto processingInfo(long customerId);
}
