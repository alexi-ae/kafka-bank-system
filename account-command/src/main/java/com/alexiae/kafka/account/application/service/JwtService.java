package com.alexiae.kafka.account.application.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface JwtService {

    boolean validateToken(String token);

    Collection<GrantedAuthority> getAuthoritiesFromToken(String token);

    String getUsernameFromToken(String token);

    String getCustomerIdFromToken(String token);
}
