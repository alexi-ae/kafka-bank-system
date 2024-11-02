package com.alexi.kafka.customer.command.adapters.in.web;

import com.alexi.kafka.customer.command.application.command.LoginCommand;
import com.alexi.kafka.customer.command.application.usercase.AuthenticationService;
import com.alexi.kafka.customer.command.domain.dto.ResponseLoginDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseLoginDto createAuthenticationToken(@RequestBody LoginCommand loginCommand)
            throws Exception {
        return authenticationService.login(loginCommand);
    }
}
