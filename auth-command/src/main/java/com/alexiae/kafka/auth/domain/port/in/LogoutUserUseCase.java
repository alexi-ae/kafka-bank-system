package com.alexiae.kafka.auth.domain.port.in;

public interface LogoutUserUseCase {
    void execute(String token);
}
