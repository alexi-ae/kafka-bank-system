package com.alexiae.kafka.customer.command.application.facade;

import com.alexiae.kafka.customer.command.application.usecases.OnboardingService;
import com.alexiae.kafka.customer.command.domain.dto.OnbResponseDto;
import com.alexiae.kafka.customer.command.application.command.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OnboardingFacade {

    @Autowired
    private OnboardingService onboardingService;

    public OnbResponseDto contact(CreateContactCommand request, long customerId) {

        return onboardingService.saveContact(request, customerId);
    }

    public OnbResponseDto contactValidate(ContactValidateCommand request, long customerId) {
        return onboardingService.contactValidate(request, customerId);
    }

    public OnbResponseDto personalInfo(CreatePersonalInfoCommand request, long customerId) {
        return onboardingService.personalInfo(request, customerId);
    }

    public OnbResponseDto identityInfo(CreateIdentityInfoCommand request, long customerId) {
        return onboardingService.identityInfo(request, customerId);
    }

    public OnbResponseDto uploadDocument(MultipartFile document, long customerId) {
        return onboardingService.uploadDocument(document, customerId);
    }

    public OnbResponseDto extraInfo(CreateExtraInfoCommand request, long customerId) {
        return onboardingService.extraInfo(request, customerId);
    }

    public OnbResponseDto processingInfo(long customerId) {
        return onboardingService.processingInfo(customerId);
    }
}
