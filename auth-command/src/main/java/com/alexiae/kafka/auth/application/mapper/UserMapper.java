package com.alexiae.kafka.auth.application.mapper;

import com.alexiae.kafka.auth.domain.command.CreateUserCommand;
import com.alexiae.kafka.auth.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    User toModel(CreateUserCommand createUserRequest);
}
