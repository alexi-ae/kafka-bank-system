package com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    boolean existsAccountEntitiesByCustomerId(Long customerId);

    boolean existsAccountEntitiesByNumber(String numberAccount);
}
