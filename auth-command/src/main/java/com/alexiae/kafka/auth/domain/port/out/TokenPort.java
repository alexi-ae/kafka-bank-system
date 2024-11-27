package com.alexiae.kafka.auth.domain.port.out;

import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.model.User;

public interface TokenPort {
    String generate(User model);

    Token getInfo(String token);
}
