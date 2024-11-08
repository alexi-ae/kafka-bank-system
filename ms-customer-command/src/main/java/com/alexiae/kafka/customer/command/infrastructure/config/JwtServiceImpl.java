package com.alexiae.kafka.customer.command.infrastructure.config;

import com.alexiae.kafka.customer.command.application.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);

        List<String> roles = claims.get("roles", List.class);


        if (roles == null) {
            return new ArrayList<>(); // Retornar una lista vacía si no hay roles
        }
        // Convertir cada rol a SimpleGrantedAuthority
        return roles.stream()
                .map(role -> "ROLE_" + role.toUpperCase())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsernameFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("email").toString();
    }

    @Override
    public String getCustomerIdFromToken(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("customerId").toString();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String getCustomerId(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("customerId").toString();
    }
}
