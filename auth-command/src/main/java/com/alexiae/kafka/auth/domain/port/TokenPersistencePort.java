package com.alexiae.kafka.auth.domain.port;

import com.alexiae.kafka.auth.domain.model.Token;

public interface TokenPersistencePort {
    void create(Token model);

    void updateRevok(String userId);
}
