package com.alexiae.kafka.auth.application.usecases;

import com.alexiae.kafka.auth.application.command.CreateUserCommand;

public interface RegisterUserUseCase {
    void execute(CreateUserCommand command);
}
