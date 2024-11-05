package com.alexiae.kafka.auth.infrastructure.adapter.repository;

import com.alexiae.kafka.auth.infrastructure.adapter.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<RoleEntity, String> {
    Optional<RoleEntity> findRolEntityByDescription(String description);

}