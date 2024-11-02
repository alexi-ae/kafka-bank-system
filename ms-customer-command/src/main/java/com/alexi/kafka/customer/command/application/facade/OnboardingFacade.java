package com.alexi.kafka.customer.command.application.facade;

import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.application.command.CreateUserCommand;
import com.alexi.kafka.customer.command.application.usercase.CustomerService;
import com.alexi.kafka.customer.command.application.usercase.UserService;
import com.alexi.kafka.customer.command.domain.dto.OnbResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    public void register(CreateUserCommand request) {
        userService.register(request);
    }

    public OnbResponseDto contact(CreateContactCommand request, long customerId) {

        return customerService.saveContact(request, customerId);
    }
}
