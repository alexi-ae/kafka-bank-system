package com.alexiae.kafka.auth.domain.port;

import com.alexiae.kafka.auth.domain.model.User;

public interface UserPersistencePort {
    void create(User model);

    User getByEmail(String email);

}
