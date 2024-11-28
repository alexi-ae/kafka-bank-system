package com.alexiae.kafka.customer.infrastructure.adapter.in.api;

import com.alexiae.kafka.customer.application.dto.*;
import com.alexiae.kafka.customer.application.service.OnboardingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/customer/ob")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping("/contact")
    public OnbResponse contact(@RequestBody CreateContactRequest request,
                               @RequestAttribute("customerId") String customerId) {
        return onboardingService.contact(request, Long.parseLong(customerId));
    }

    @PostMapping("/contact-validate")
    public OnbResponse contactValidate(@RequestBody ContactValidateRequest request,
                                       @RequestAttribute("customerId") String customerId) {
        return onboardingService.contactValidate(request, Long.parseLong(customerId));
    }

    @PostMapping("/personal-info")
    public OnbResponse personalInfo(@RequestBody CreatePersonalInfoRequest request,
                                    @RequestAttribute("customerId") String customerId) {
        return onboardingService.personalInfo(request, Long.parseLong(customerId));
    }

    @PostMapping("/identity-info")
    public OnbResponse identityInfo(@RequestBody CreateIdentityInfoRequest request,
                                    @RequestAttribute("customerId") String customerId) {
        return onboardingService.identityInfo(request, Long.parseLong(customerId));
    }

    @PostMapping("/upload-document")
    public OnbResponse uploadDocument(@RequestPart(value = "file") MultipartFile document, @RequestAttribute("customerId") String customerId) {
        return onboardingService.uploadDocument(document, Long.parseLong(customerId));
    }

    @PostMapping("/extra-info")
    public OnbResponse extraInfo(@RequestBody CreateExtraInfoRequest request,
                                 @RequestAttribute("customerId") String customerId) {
        return onboardingService.extraInfo(request, Long.parseLong(customerId));
    }

    @PostMapping("/processing-info")
    public OnbResponse processingInfo(@RequestAttribute("customerId") String customerId) {
        return onboardingService.processingInfo(Long.parseLong(customerId));
    }
}
