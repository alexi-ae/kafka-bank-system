package com.alexiae.kafka.auth.domain.port.in;

import com.alexiae.kafka.auth.domain.command.CreateUserCommand;

public interface RegisterUserUseCase {
    void execute(CreateUserCommand command);
}
