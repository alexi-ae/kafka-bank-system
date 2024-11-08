package com.alexiae.kafka.account.application.mapper;

import com.alexiae.kafka.account.application.command.CreateAccountCommand;
import com.alexiae.kafka.account.domain.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    Account toModel(CreateAccountCommand command);
}
