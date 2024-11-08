package com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.customer.domain.model.File;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.entity.FilesEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileEntityMapper {
    FilesEntity toEntity(File files);

    File toModel(FilesEntity save);
}
