package com.alexiae.kafka.auth.domain.port;

import com.alexiae.kafka.auth.domain.model.Role;

public interface RolePersistencePort {
    void create(Role model);
}
