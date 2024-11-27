package com.alexiae.kafka.auth.application.usecases;

import com.alexiae.kafka.auth.application.mapper.UserMapper;
import com.alexiae.kafka.auth.application.port.out.CustomerEventPublisher;
import com.alexiae.kafka.auth.domain.command.CreateUserCommand;
import com.alexiae.kafka.auth.domain.event.CreateCustomerEvent;
import com.alexiae.kafka.auth.domain.model.User;
import com.alexiae.kafka.auth.domain.port.in.RegisterUserUseCase;
import com.alexiae.kafka.auth.domain.port.out.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private CustomerEventPublisher customerEventPublisher;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void execute(CreateUserCommand command) {
        User user = userPersistencePort.create(userMapper.toModel(command));
        customerEventPublisher.publishCustomerCreatedEvent(CreateCustomerEvent.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .build());
    }
}
