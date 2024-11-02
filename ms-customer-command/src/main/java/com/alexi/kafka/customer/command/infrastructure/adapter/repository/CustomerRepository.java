package com.alexi.kafka.customer.command.infrastructure.adapter.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findCustomerEntityByUserEmail(String email);

    boolean existsCustomerEntityByUserEmail(String email);
}
