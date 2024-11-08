package com.alexi.kafka.customer.command.adapters.in.web;

import com.alexi.kafka.customer.command.application.command.*;
import com.alexi.kafka.customer.command.application.facade.OnboardingFacade;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/ob")
public class OnboardingController {

    @Autowired
    private OnboardingFacade onboardingFacade;

    @PostMapping("/contact")
    public OnbResponseDto contact(@RequestBody CreateContactCommand request,
                                  @RequestAttribute("customerId") String customerId) {
        return onboardingFacade.contact(request, Long.parseLong(customerId));
    }

    @PostMapping("/contact-validate")
    public OnbResponseDto contactValidate(@RequestBody ContactValidateCommand request,
                                          @RequestAttribute("customerId") String customerId) {
        return onboardingFacade.contactValidate(request, Long.parseLong(customerId));
    }

    @PostMapping("/personal-info")
    public OnbResponseDto personalInfo(@RequestBody CreatePersonalInfoCommand request,
                                       @RequestAttribute("customerId") String customerId) {
        return onboardingFacade.personalInfo(request, Long.parseLong(customerId));
    }

    @PostMapping("/identity-info")
    public OnbResponseDto identityInfo(@RequestBody CreateIdentityInfoCommand request,
                                       @RequestAttribute("customerId") String customerId) {
        return onboardingFacade.identityInfo(request, Long.parseLong(customerId));
    }

    @PostMapping("/upload-document")
    public OnbResponseDto uploadDocument(@RequestPart(value = "file") MultipartFile document, @RequestAttribute("customerId") String customerId) {
        return onboardingFacade.uploadDocument(document, Long.parseLong(customerId));
    }

    @PostMapping("/extra-info")
    public OnbResponseDto extraInfo(@RequestBody CreateExtraInfoCommand request,
                                    @RequestAttribute("customerId") String customerId) {
        return onboardingFacade.extraInfo(request, Long.parseLong(customerId));
    }

    @PostMapping("/processing-info")
    public OnbResponseDto processingInfo(@RequestAttribute("customerId") String customerId) {
        return onboardingFacade.processingInfo(Long.parseLong(customerId));
    }
}
