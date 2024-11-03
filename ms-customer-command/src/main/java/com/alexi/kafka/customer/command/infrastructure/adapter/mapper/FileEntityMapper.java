package com.alexi.kafka.customer.command.infrastructure.adapter.mapper;

import com.alexi.kafka.customer.command.domain.model.File;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.FilesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper {
    FilesEntity toEntity(File files);

    File toModel(FilesEntity save);
}