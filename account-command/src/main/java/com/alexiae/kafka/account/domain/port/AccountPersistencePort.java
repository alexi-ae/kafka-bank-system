package com.alexiae.kafka.account.domain.port;

import com.alexiae.kafka.account.domain.model.Account;

public interface AccountPersistencePort {
    Account create(Account account);

    boolean existsByCustomerId(Long customerId);

    boolean existsByNumber(String numberAccount);
}
