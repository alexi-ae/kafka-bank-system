package com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findRolEntityByDescription(String description);
}
