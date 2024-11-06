package com.alexiae.kafka.auth.application.usecases.impl;

import com.alexiae.kafka.auth.application.services.TokenRedisService;
import com.alexiae.kafka.auth.application.services.TokenService;
import com.alexiae.kafka.auth.application.usecases.LogoutUserUseCase;
import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.port.TokenPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class LogoutUserUseCaseImpl implements LogoutUserUseCase {

    @Autowired
    private TokenPersistencePort tokenPersistencePort;

    @Autowired
    private TokenRedisService tokenRedisService;

    @Autowired
    private TokenService tokenService;


    @Override
    public void execute(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Token tokenInfo = tokenService.getInfo(token);
        long expiration = tokenInfo.getExpiresAt().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        tokenRedisService.revokeToken(token, expiration);
        tokenPersistencePort.updateRevok(token);
    }
}
