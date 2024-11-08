package com.alexiae.kafka.account.application.usecases;

import com.alexiae.kafka.account.application.command.CreateAccountCommand;
import com.alexiae.kafka.account.domain.model.Account;

public interface CreateAccountService {

    Account execute(CreateAccountCommand command);
}
