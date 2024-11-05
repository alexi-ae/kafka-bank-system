package com.alexiae.kafka.auth.infrastructure.adapter.mapper;

import com.alexiae.kafka.auth.domain.model.Token;
import com.alexiae.kafka.auth.infrastructure.adapter.entity.TokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenEntityMapper {
    TokenEntity toEntity(Token token);

    Token toModel(TokenEntity save);
}
