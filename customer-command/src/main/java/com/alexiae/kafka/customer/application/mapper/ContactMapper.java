package com.alexiae.kafka.customer.application.mapper;


import com.alexiae.kafka.customer.domain.command.CreateContactCommand;
import com.alexiae.kafka.customer.domain.command.CreatePersonalInfoCommand;
import com.alexiae.kafka.customer.domain.model.Contact;
import com.alexiae.kafka.customer.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toModel(@MappingTarget Contact contact, CreateContactCommand createContactCommand);

    Customer fromPersonalInfo(@MappingTarget Customer customer, CreatePersonalInfoCommand request);
}
