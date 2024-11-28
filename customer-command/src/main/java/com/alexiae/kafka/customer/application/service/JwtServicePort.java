package com.alexiae.kafka.customer.application.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface JwtServicePort {

    boolean validateToken(String token);

    Collection<GrantedAuthority> getAuthoritiesFromToken(String token);

    String getUsernameFromToken(String token);

    String getCustomerIdFromToken(String token);
}
