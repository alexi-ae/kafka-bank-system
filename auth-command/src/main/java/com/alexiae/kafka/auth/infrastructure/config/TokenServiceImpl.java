package com.alexiae.kafka.auth.infrastructure.config;

import com.alexiae.kafka.auth.application.services.TokenService;
import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.Key;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TokenServiceImpl implements TokenService {

    @Value("${security.jwt.time}")
    private long JWT_TOKEN_VALIDITY;

    @Value("${security.jwt.secret}")
    private String secretKey;

    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("roles", user.getRoles());
        claims.put("state",
                Objects.nonNull(user.getCustomerId()) ? user.getStatus() : "PENDING");
        claims.put("customerId",
                Objects.nonNull(user.getCustomerId()) ? user.getCustomerId() : BigDecimal.ZERO);
        return createToken(claims, user.getEmail());
    }


    public Token getInfo(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return Token.builder()
                .userId(claims.get("id").toString())
                .createdAt(claims.getIssuedAt().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .expiresAt(claims.getExpiration().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime())
                .revoked(Boolean.FALSE)
                .build();
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getCustomerId(String token) {
        final Claims claims = getAllClaimsFromToken(token);
        return claims.get("customerId").toString();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
