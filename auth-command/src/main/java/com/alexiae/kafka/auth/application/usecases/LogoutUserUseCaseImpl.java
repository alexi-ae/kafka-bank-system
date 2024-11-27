package com.alexiae.kafka.auth.application.usecases;

import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.port.in.LogoutUserUseCase;
import com.alexiae.kafka.auth.domain.port.out.TokenPort;
import com.alexiae.kafka.auth.domain.port.out.TokenRedisPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

@Service
public class LogoutUserUseCaseImpl implements LogoutUserUseCase {

    @Autowired
    private TokenRedisPort tokenRedisPort;

    @Autowired
    private TokenPort tokenPort;


    @Override
    public void execute(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        Token tokenInfo = tokenPort.getInfo(token);
        long ttlInMillis = Duration.between(Instant.now(), tokenInfo.getExpiresAt().atZone(ZoneId.systemDefault()).toInstant()).toMillis();

        if (ttlInMillis > 0) {
            tokenRedisPort.revokeToken(token, ttlInMillis);
        }
    }
}
