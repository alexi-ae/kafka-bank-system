package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.transaction.domain.model.TransactionMovement;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity.TransactionMovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMovementEntityMapper {


    @Mapping(target = "creationDate", ignore = true)
    TransactionMovementEntity toEntity(TransactionMovement model);

    TransactionMovement toModel(TransactionMovementEntity save);
}
