package com.alexi.kafka.customer.command.application.usercase;

import com.alexi.kafka.customer.command.application.command.CreateUserCommand;
import com.alexi.kafka.customer.command.domain.dto.UserDto;

public interface UserService {

    UserDto register(CreateUserCommand createUserCommand);
}
