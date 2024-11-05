package com.alexiae.kafka.auth.application.usecases;

import com.alexiae.kafka.auth.application.command.LoginCommand;
import com.alexiae.kafka.auth.domain.dto.ResponseLoginDto;

public interface LoginUserUseCase {
    ResponseLoginDto execute(LoginCommand command);
}
