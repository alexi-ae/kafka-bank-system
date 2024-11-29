package com.alexiae.kafka.account.domain.port.in;

import com.alexiae.kafka.account.domain.command.CreateAccountCommand;
import com.alexiae.kafka.account.domain.model.Account;

public interface CreateAccountUseCase {

    Account execute(CreateAccountCommand command);
}
