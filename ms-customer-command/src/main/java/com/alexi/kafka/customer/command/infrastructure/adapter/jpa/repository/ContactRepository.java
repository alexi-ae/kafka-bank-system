package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
