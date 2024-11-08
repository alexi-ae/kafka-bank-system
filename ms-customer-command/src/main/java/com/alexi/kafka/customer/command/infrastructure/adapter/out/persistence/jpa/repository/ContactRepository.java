package com.alexi.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
