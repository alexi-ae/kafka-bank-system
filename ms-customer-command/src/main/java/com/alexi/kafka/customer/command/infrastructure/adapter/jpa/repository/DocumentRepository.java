package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
