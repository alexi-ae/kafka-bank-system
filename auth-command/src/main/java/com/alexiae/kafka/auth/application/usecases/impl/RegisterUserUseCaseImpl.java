package com.alexiae.kafka.auth.application.usecases.impl;

import com.alexiae.kafka.auth.application.command.CreateUserCommand;
import com.alexiae.kafka.auth.application.mapper.UserMapper;
import com.alexiae.kafka.auth.application.usecases.RegisterUserUseCase;
import com.alexiae.kafka.auth.domain.port.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void execute(CreateUserCommand command) {
        userPersistencePort.create(userMapper.toModel(command));
    }
}
