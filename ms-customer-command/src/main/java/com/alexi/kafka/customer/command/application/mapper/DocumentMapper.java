package com.alexi.kafka.customer.command.application.mapper;


import com.alexi.kafka.customer.command.application.command.CreateIdentityInfoCommand;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toModel(@MappingTarget Document identityInfo, CreateIdentityInfoCommand command);

    Customer fromDocument(@MappingTarget Customer customer, CreateIdentityInfoCommand command);
}
