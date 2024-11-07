package com.alexi.kafka.customer.command.application.mapper;


import com.alexi.kafka.customer.command.application.command.CreateContactCommand;
import com.alexi.kafka.customer.command.application.command.CreatePersonalInfoCommand;
import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    Contact toModel(@MappingTarget Contact contact, CreateContactCommand createContactCommand);

    Customer fromPersonalInfo(@MappingTarget Customer customer, CreatePersonalInfoCommand request);
}
