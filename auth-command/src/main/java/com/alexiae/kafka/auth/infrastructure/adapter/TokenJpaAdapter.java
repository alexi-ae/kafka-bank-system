package com.alexiae.kafka.auth.infrastructure.adapter;

import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.domain.port.TokenPersistencePort;
import com.alexiae.kafka.auth.infrastructure.adapter.entity.TokenEntity;
import com.alexiae.kafka.auth.infrastructure.adapter.mapper.TokenEntityMapper;
import com.alexiae.kafka.auth.infrastructure.adapter.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TokenJpaAdapter implements TokenPersistencePort {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenEntityMapper tokenEntityMapper;

    @Override
    public void create(Token model) {
        TokenEntity tokenEntity = tokenEntityMapper.toEntity(model);
        tokenRepository.save(tokenEntity);
    }

    @Override
    public void updateRevok(String userId) {
        List<TokenEntity> tokenEntityList =
                tokenRepository.getTokenEntityByUserIdAndRevokedIsFalse(userId).stream().map(x -> {
                    x.setRevoked(Boolean.TRUE);
                    return x;
                }).toList();
        if (Boolean.FALSE.equals(tokenEntityList.isEmpty())) {
            tokenRepository.saveAll(tokenEntityList);
        }
    }
}
