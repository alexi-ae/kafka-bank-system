package com.alexi.kafka.customer.command.infrastructure.adapter.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long> {

    Optional<RolEntity> findRolEntityByDescription(String description);
}
