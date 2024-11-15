package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.transaction.domain.model.Transaction;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionEntityMapper {

    @Mapping(target = "movements", ignore = true)
    TransactionEntity toEntity(Transaction model);


    Transaction toModel(TransactionEntity save);
}
