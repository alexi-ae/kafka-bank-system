package com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);

}
