package com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.customer.command.domain.model.File;
import com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.FilesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper {
    FilesEntity toEntity(File files);

    File toModel(FilesEntity save);
}
