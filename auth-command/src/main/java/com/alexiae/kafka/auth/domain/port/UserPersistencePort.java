package com.alexiae.kafka.auth.domain.port;

import com.alexiae.kafka.auth.domain.model.User;

public interface UserPersistencePort {
    User create(User model);

    void updateCustomerId(String userId, long customerId);


    User getByEmail(String email);

}
