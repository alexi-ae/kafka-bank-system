package com.alexiae.kafka.auth.application.services;

import com.alexiae.kafka.auth.application.dto.CreateUserRequest;
import com.alexiae.kafka.auth.application.dto.LoginRequest;
import com.alexiae.kafka.auth.application.dto.LoginResponse;
import com.alexiae.kafka.auth.application.mapper.AuthMapper;
import com.alexiae.kafka.auth.domain.port.in.LoginUserUseCase;
import com.alexiae.kafka.auth.domain.port.in.LogoutUserUseCase;
import com.alexiae.kafka.auth.domain.port.in.RegisterUserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private LoginUserUseCase loginUserUseCase;

    @Autowired
    private RegisterUserUseCase registerUserUseCase;

    @Autowired
    private LogoutUserUseCase logoutUserUseCase;

    @Autowired
    private AuthMapper authMapper;

    public LoginResponse login(LoginRequest command) {
        return authMapper.toLoginResponse(loginUserUseCase.execute(authMapper.toLoginRequest(command)));
    }

    public void register(CreateUserRequest request) {
        registerUserUseCase.execute(authMapper.toCreateUserCommand(request));
    }

    public void logout(String token) {
        logoutUserUseCase.execute(token);
    }
}
