package com.alexiae.kafka.customer.application.mapper;


import com.alexiae.kafka.customer.application.dto.*;
import com.alexiae.kafka.customer.domain.command.*;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OnboardingMapper {
    OnbResponse toResponse(ObnInfo save);

    CreateContactCommand toContactRequest(CreateContactRequest request);

    ContactValidateCommand toContactValidate(ContactValidateRequest request);

    CreatePersonalInfoCommand toPersonalInfo(CreatePersonalInfoRequest request);

    CreateIdentityInfoCommand toIdentityInfo(CreateIdentityInfoRequest request);

    CreateExtraInfoCommand toExtraInfo(CreateExtraInfoRequest request);
}
