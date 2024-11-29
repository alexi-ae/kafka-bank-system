package com.alexiae.kafka.account.domain.port.out;

import com.alexiae.kafka.account.domain.model.Account;

public interface AccountPersistencePort {
    Account create(Account account);

    boolean existsByCustomerId(Long customerId);

    boolean existsByNumber(String numberAccount);

    Account getById(Long id);

    Account update(Account account);

}
