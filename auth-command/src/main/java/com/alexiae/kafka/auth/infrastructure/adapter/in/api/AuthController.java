package com.alexiae.kafka.auth.infrastructure.adapter.in.api;


import com.alexiae.kafka.auth.application.dto.CreateUserRequest;
import com.alexiae.kafka.auth.application.dto.LoginRequest;
import com.alexiae.kafka.auth.application.dto.LoginResponse;
import com.alexiae.kafka.auth.application.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody CreateUserRequest request) {
        authenticationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse createAuthenticationToken(@RequestBody LoginRequest loginCommand) {
        return authenticationService.login(loginCommand);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader(value = "Authorization", required = true) String token) {
        authenticationService.logout(token);
    }
}
