package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.mapper;

import com.alexi.kafka.customer.command.domain.model.Document;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.DocumentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentEntityMapper {
    DocumentEntity toEntity(Document contact);

    Document toModel(DocumentEntity save);
}
