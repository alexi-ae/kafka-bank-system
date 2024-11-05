package com.alexiae.kafka.auth.application.usecases.impl;

import com.alexiae.kafka.auth.application.command.LoginCommand;
import com.alexiae.kafka.auth.application.services.TokenService;
import com.alexiae.kafka.auth.application.usecases.LoginUserUseCase;
import com.alexiae.kafka.auth.domain.dto.ResponseLoginDto;
import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.model.User;
import com.alexiae.kafka.auth.domain.port.TokenPersistencePort;
import com.alexiae.kafka.auth.domain.port.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private TokenPersistencePort tokenPersistencePort;

    @Autowired
    private TokenService tokenService;

    @Override
    public ResponseLoginDto execute(LoginCommand command) {
        User user = userPersistencePort.getByEmail(command.getEmail());
        String tokenString = tokenService.generateToken(user);
        Token tokenInfo = tokenService.getInfo(tokenString);
        tokenPersistencePort.create(tokenInfo);
        return ResponseLoginDto.builder()
                .token(tokenString)
                .build();
    }
}
