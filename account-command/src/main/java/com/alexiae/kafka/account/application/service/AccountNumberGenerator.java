package com.alexiae.kafka.account.application.service;


import com.alexiae.kafka.account.domain.port.AccountPersistencePort;
import com.alexiae.kafka.account.infrastructure.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountNumberGenerator {

    @Autowired
    private AccountPersistencePort accountPersistencePort;

    public String number() {
        String numberAccount;
        do {
            numberAccount = AccountUtils.numberAccountGenerate();
        } while (accountPersistencePort.existsByNumber(numberAccount));
        return numberAccount;
    }

    public String numberCci(String numberAccount, String base) {
        return AccountUtils.generarNumeroCCI(numberAccount, base);
    }
}
