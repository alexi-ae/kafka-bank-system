package com.alexi.kafka.customer.command.application.usercase;

import com.alexi.kafka.customer.command.application.command.LoginCommand;
import com.alexi.kafka.customer.command.domain.dto.ResponseLoginDto;

public interface AuthenticationService {

    ResponseLoginDto login(LoginCommand loginCommand);
}
