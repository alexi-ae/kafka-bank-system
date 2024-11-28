package com.alexiae.kafka.customer.application.service;

import com.alexiae.kafka.customer.application.dto.*;
import com.alexiae.kafka.customer.application.mapper.OnboardingMapper;
import com.alexiae.kafka.customer.domain.port.in.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class OnboardingService {

    @Autowired
    private ManageContactUseCase manageContactUseCase;

    @Autowired
    private ManageExtraDataUseCase manageExtraDataUseCase;

    @Autowired
    private ManageFileUseCase manageFileUseCase;

    @Autowired
    private ManageIdentityUseCase manageIdentityUseCase;

    @Autowired
    private ManagePersonalUseCase managePersonalUseCase;

    @Autowired
    private OnboardingUseCase onboardingUseCase;

    @Autowired
    private OnboardingMapper onboardingMapper;

    public OnbResponse contact(CreateContactRequest request, long customerId) {

        return onboardingMapper.toResponse(manageContactUseCase.save(onboardingMapper.toContactRequest(request), customerId));
    }

    public OnbResponse contactValidate(ContactValidateRequest request, long customerId) {
        return onboardingMapper.toResponse(manageContactUseCase.validate(onboardingMapper.toContactValidate(request), customerId));
    }

    public OnbResponse personalInfo(CreatePersonalInfoRequest request, long customerId) {
        return onboardingMapper.toResponse(managePersonalUseCase.save( onboardingMapper.toPersonalInfo(request), customerId));
    }

    public OnbResponse identityInfo(CreateIdentityInfoRequest request, long customerId) {
        return onboardingMapper.toResponse(manageIdentityUseCase.save(onboardingMapper.toIdentityInfo(request), customerId));
    }

    public OnbResponse uploadDocument(MultipartFile document, long customerId) {
        return onboardingMapper.toResponse(manageFileUseCase.uploadObn(document, customerId));
    }

    public OnbResponse extraInfo(CreateExtraInfoRequest request, long customerId) {
        return onboardingMapper.toResponse(manageExtraDataUseCase.save(onboardingMapper.toExtraInfo(request), customerId));
    }

    public OnbResponse processingInfo(long customerId) {
        return onboardingMapper.toResponse(onboardingUseCase.process(customerId));
    }
}
