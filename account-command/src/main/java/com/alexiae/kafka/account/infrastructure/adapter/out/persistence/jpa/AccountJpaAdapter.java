package com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa;

import com.alexiae.kafka.account.domain.model.Account;
import com.alexiae.kafka.account.domain.port.out.AccountPersistencePort;
import com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.mapper.AccountEntityMapper;
import com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountJpaAdapter implements AccountPersistencePort {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Override
    public Account create(Account account) {
        return accountEntityMapper.toModel(accountRepository.save(accountEntityMapper.toEntity(account)));
    }

    @Override
    public boolean existsByCustomerId(Long customerId) {
        return accountRepository.existsAccountEntitiesByCustomerId(customerId);
    }

    @Override
    public boolean existsByNumber(String numberAccount) {
        return accountRepository.existsAccountEntitiesByNumber(numberAccount);
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findById(id).map(accountEntityMapper::toModel).orElse(null);
    }

    @Override
    public Account update(Account account) {
        return accountEntityMapper.toModel(accountRepository.save(accountEntityMapper.toEntity(account)));
    }
}
