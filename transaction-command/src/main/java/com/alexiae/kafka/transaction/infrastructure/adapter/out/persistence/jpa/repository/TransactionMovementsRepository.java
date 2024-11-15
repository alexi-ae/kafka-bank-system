package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity.TransactionMovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionMovementsRepository extends JpaRepository<TransactionMovementEntity, Long> {

}
