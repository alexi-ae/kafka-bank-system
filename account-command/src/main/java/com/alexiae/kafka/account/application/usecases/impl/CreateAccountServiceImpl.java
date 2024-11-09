package com.alexiae.kafka.account.application.usecases.impl;

import com.alexiae.kafka.account.application.command.CreateAccountCommand;
import com.alexiae.kafka.account.application.service.AccountNumberGenerator;
import com.alexiae.kafka.account.application.usecases.CreateAccountService;
import com.alexiae.kafka.account.domain.enums.AccountCurrency;
import com.alexiae.kafka.account.domain.enums.AccountStatus;
import com.alexiae.kafka.account.domain.enums.AccountType;
import com.alexiae.kafka.account.domain.model.Account;
import com.alexiae.kafka.account.domain.port.AccountPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
    @Autowired
    private AccountPersistencePort accountPersistencePort;

    @Autowired
    private AccountNumberGenerator accountNumberGenerator;

    @Override
    public Account execute(CreateAccountCommand command) {

        boolean exists = accountPersistencePort.existsByCustomerId(command.getCustomerId());

        if (Boolean.TRUE.equals(exists)) {
            return null;
        }

        String numberAccount = accountNumberGenerator.number();
        String numberAccountCci = accountNumberGenerator.numberCci(numberAccount, "001");
        String holder = command.getFirstName() + command.getLastName();

        Account account = Account.builder()
                .number(numberAccount)
                .numberCci(numberAccountCci)
                .isMain(Boolean.TRUE)
                .holder(holder)
                .type(AccountType.AHORROS)
                .type(AccountType.AHORROS)
                .currency(AccountCurrency.SOLES)
                .currentBalance(BigDecimal.ZERO)
                .openingDate(new Date())
                .status(AccountStatus.ACTIVE)
                .dailyTransferLimit(BigDecimal.valueOf(2000.00))
                .customerId(command.getCustomerId())
                .build();

        return accountPersistencePort.create(account);
    }
}
