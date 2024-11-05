package com.alexiae.kafka.auth.infrastructure.adapter.repository;

import com.alexiae.kafka.auth.infrastructure.adapter.entity.TokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends MongoRepository<TokenEntity, String> {

    List<TokenEntity> getTokenEntityByUserIdAndRevokedIsFalse(String userId);

}
