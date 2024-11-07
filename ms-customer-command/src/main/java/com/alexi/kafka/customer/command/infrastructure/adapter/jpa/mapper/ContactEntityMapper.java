package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.mapper;

import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.ContactEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactEntityMapper {
    ContactEntity toEntity(Contact contact);

    Contact toModel(ContactEntity save);
}
