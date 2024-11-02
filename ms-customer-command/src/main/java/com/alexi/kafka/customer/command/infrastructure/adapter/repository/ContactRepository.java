package com.alexi.kafka.customer.command.infrastructure.adapter.repository;

import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    @Query(value = "select * from contact  " +
            "where ", nativeQuery = true)
    Contact findContactEntitiesByCustomerId();
}
