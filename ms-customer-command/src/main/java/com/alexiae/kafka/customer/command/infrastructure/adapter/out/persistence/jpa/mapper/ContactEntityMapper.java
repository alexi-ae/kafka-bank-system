package com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexiae.kafka.customer.command.domain.model.Contact;
import com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.ContactEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactEntityMapper {
    ContactEntity toEntity(Contact contact);

    Contact toModel(ContactEntity save);
}
