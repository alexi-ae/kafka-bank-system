package com.alexi.kafka.customer.command.application.mapper;


import com.alexi.kafka.customer.command.application.command.CreateUserCommand;
import com.alexi.kafka.customer.command.domain.dto.UserDto;
import com.alexi.kafka.customer.command.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toModel(CreateUserCommand createUserRequest);

    UserDto toDto(User user);
}
