package com.alexiae.kafka.auth.domain.port.in;

import com.alexiae.kafka.auth.domain.command.LoginCommand;
import com.alexiae.kafka.auth.domain.model.AuthToken;

public interface LoginUserUseCase {
    AuthToken execute(LoginCommand command);
}
