package com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.account.domain.model.Account;
import com.alexiae.kafka.account.infrastructure.adapter.out.persistence.jpa.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountEntityMapper {
    AccountEntity toEntity(Account account);

    Account toModel(AccountEntity save);
}
