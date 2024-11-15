package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

}
