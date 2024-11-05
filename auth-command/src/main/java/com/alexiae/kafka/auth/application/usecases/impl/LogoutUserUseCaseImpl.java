package com.alexiae.kafka.auth.application.usecases.impl;

import com.alexiae.kafka.auth.application.usecases.LogoutUserUseCase;
import com.alexiae.kafka.auth.domain.port.TokenPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutUserUseCaseImpl implements LogoutUserUseCase {

    @Autowired
    private TokenPersistencePort tokenPersistencePort;

    @Override
    public void execute(String userId) {
        tokenPersistencePort.updateRevok(userId);
    }
}
