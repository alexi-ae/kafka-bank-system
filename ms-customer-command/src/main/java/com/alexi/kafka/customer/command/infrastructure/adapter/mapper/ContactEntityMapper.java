package com.alexi.kafka.customer.command.infrastructure.adapter.mapper;

import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.ContactEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactEntityMapper {
    ContactEntity toEntity(Contact contact);

    Contact toModel(ContactEntity save);
}
