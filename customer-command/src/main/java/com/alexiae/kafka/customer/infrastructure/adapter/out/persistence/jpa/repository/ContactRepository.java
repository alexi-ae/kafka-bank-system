package com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
}
