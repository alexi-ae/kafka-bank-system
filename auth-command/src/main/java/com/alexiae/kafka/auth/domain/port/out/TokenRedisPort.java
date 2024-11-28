package com.alexiae.kafka.auth.domain.port.out;

public interface TokenRedisPort {
    void revokeToken(String token, long expirationMs);

    boolean isTokenRevoked(String token);
}