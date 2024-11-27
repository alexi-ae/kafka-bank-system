package com.alexiae.kafka.auth.application.mapper;

import com.alexiae.kafka.auth.application.dto.CreateUserRequest;
import com.alexiae.kafka.auth.application.dto.LoginRequest;
import com.alexiae.kafka.auth.application.dto.LoginResponse;
import com.alexiae.kafka.auth.domain.command.CreateUserCommand;
import com.alexiae.kafka.auth.domain.command.LoginCommand;
import com.alexiae.kafka.auth.domain.model.AuthToken;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    LoginResponse toLoginResponse(AuthToken authToken);

    LoginCommand toLoginRequest(LoginRequest command);

    CreateUserCommand toCreateUserCommand(CreateUserRequest request);
}
