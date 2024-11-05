package com.alexiae.kafka.auth.application.services;

import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.model.User;

public interface TokenService {
    String generateToken(User model);

    Token getInfo(String token);
}
