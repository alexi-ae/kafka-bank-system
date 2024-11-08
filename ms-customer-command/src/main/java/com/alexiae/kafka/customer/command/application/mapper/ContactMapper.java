package com.alexiae.kafka.customer.command.application.mapper;


import com.alexiae.kafka.customer.command.application.command.CreateContactCommand;
import com.alexiae.kafka.customer.command.application.command.CreatePersonalInfoCommand;
import com.alexiae.kafka.customer.command.domain.model.Contact;
import com.alexiae.kafka.customer.command.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toModel(@MappingTarget Contact contact, CreateContactCommand createContactCommand);

    Customer fromPersonalInfo(@MappingTarget Customer customer, CreatePersonalInfoCommand request);
}
