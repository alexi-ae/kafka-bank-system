package com.alexiae.kafka.transaction.application.mapper;

import com.alexiae.kafka.transaction.domain.dto.TransactionInitiatedRequest;
import com.alexiae.kafka.transaction.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "status", expression = "java(com.alexiae.kafka.transaction.domain.enums.TransactionStatus.PENDING)")
    @Mapping(target = "movements", ignore = true)
    Transaction toModel(TransactionInitiatedRequest request);
}
