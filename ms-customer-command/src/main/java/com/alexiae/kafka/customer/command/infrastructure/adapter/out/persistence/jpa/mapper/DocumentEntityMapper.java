package com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.customer.command.domain.model.Document;
import com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.DocumentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentEntityMapper {
    DocumentEntity toEntity(Document contact);

    Document toModel(DocumentEntity save);
}
