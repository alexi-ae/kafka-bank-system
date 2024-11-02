package com.alexi.kafka.customer.command.adapters.in.web;

import com.alexi.kafka.customer.command.application.command.ContactValidateCommand;
import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.application.command.CreateUserCommand;
import com.alexi.kafka.customer.command.application.facade.OnboardingFacade;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/ob")
public class OnboardingController {

    @Autowired
    private OnboardingFacade onboardingFacade;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody CreateUserCommand request) {
        onboardingFacade.register(request);
    }

    @PostMapping("/contact")
    public OnbResponseDto contact(@RequestBody CreateContactCommand request,
                                  @RequestAttribute("customerId") long customerId) {
        return onboardingFacade.contact(request, customerId);
    }

    @PostMapping("/contact-validate")
    public OnbResponseDto contactValidate(@RequestBody ContactValidateCommand request,
                                          @RequestAttribute("customerId") long customerId) {
        return onboardingFacade.contactValidate(request, customerId);
    }


}
