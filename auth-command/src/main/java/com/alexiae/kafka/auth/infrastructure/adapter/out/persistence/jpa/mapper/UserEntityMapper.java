package com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.auth.domain.model.User;
import com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User user);

    @Mapping(target = "customer.user", ignore = true)
    User toModel(UserEntity save);
}
