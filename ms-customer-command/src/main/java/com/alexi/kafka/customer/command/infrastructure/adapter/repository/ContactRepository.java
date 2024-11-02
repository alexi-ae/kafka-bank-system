package com.alexi.kafka.customer.command.infrastructure.adapter.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
