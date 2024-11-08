package com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.customer.domain.model.ExtraInfo;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.entity.ExtraInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtraInfoEntityMapper {
    ExtraInfoEntity toEntity(ExtraInfo extraInfo);

    ExtraInfo toModel(ExtraInfoEntity save);
}
