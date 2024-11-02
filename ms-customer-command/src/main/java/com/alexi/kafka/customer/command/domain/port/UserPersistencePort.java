package com.alexi.kafka.customer.command.domain.port;

import com.alexi.kafka.customer.command.domain.model.User;

public interface UserPersistencePort {

    User create(User user);

    User findByEmail(String email);

    boolean existsByEmail(String email);
}
