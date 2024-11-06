package com.alexiae.kafka.auth.application.services;

public interface TokenRedisService {

    void revokeToken(String token, long expirationMs);

    boolean isTokenRevoked(String token);
}
