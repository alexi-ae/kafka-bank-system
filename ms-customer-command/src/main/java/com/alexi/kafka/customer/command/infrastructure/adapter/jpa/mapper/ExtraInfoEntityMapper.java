package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.mapper;

import com.alexi.kafka.customer.command.domain.model.ExtraInfo;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.ExtraInfoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExtraInfoEntityMapper {
    ExtraInfoEntity toEntity(ExtraInfo extraInfo);

    ExtraInfo toModel(ExtraInfoEntity save);
}
