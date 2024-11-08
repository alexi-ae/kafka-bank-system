package com.alexiae.kafka.auth.infrastructure.adapter.in.api;


import com.alexiae.kafka.auth.application.command.CreateUserCommand;
import com.alexiae.kafka.auth.application.command.LoginCommand;
import com.alexiae.kafka.auth.application.usecases.LoginUserUseCase;
import com.alexiae.kafka.auth.application.usecases.LogoutUserUseCase;
import com.alexiae.kafka.auth.application.usecases.RegisterUserUseCase;
import com.alexiae.kafka.auth.domain.dto.ResponseLoginDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    private LoginUserUseCase loginUserUseCase;

    @Autowired
    private LogoutUserUseCase logoutUserUseCase;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody CreateUserCommand request) {
        registerUserUseCase.execute(request);
    }

    @PostMapping("/login")
    public ResponseLoginDto createAuthenticationToken(@RequestBody LoginCommand loginCommand) {
        return loginUserUseCase.execute(loginCommand);
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader(value = "Authorization", required = true) String token) {
        logoutUserUseCase.execute(token);
    }
}
