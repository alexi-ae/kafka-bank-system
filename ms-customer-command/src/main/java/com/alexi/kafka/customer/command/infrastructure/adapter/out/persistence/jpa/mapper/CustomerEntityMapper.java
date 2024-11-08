package com.alexi.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.mapper;

import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    CustomerEntity toEntity(Customer customer);

    Customer toModel(CustomerEntity entity);
}
