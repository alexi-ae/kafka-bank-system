package com.alexiae.kafka.auth.infrastructure.config;

import com.alexiae.kafka.auth.application.services.TokenRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenRedisServiceImpl implements TokenRedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String REVOKED_TOKEN_PREFIX = "revokedToken:";

    @Override
    public void revokeToken(String token, long expirationMs) {
        String key = REVOKED_TOKEN_PREFIX + token;
        redisTemplate.opsForValue().set(key, "revoked", expirationMs, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenRevoked(String token) {
        String key = REVOKED_TOKEN_PREFIX + token;
        return redisTemplate.hasKey(key);
    }

}