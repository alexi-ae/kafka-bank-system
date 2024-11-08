package com.alexiae.kafka.customer.application.mapper;


import com.alexiae.kafka.customer.application.command.CreateIdentityInfoCommand;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toModel(@MappingTarget Document identityInfo, CreateIdentityInfoCommand command);

    Customer fromDocument(@MappingTarget Customer customer, CreateIdentityInfoCommand command);
}
