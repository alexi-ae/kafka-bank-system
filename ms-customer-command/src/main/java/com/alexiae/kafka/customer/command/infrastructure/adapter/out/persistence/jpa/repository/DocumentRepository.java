package com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
