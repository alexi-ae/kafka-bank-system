package com.alexi.kafka.customer.command.infrastructure.adapter.mapper;

import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    CustomerEntity toEntity(Customer user);

    @Mapping(target = "user.customer", ignore = true)
    Customer toModel(CustomerEntity save);
}
