package com.alexi.kafka.customer.command.infrastructure.adapter.mapper;

import com.alexi.kafka.customer.command.domain.model.User;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserEntity toEntity(User user);

    @Mapping(target = "customer.user", ignore = true)
    User toModel(UserEntity save);
}
