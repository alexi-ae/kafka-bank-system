package com.alexi.kafka.customer.command.infrastructure.adapter.jpa;

import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.CustomerEntity;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.mapper.CustomerEntityMapper;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomerJpaAdapter implements CustomerPersistencePort {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEntityMapper customerEntityMapper;

    @Override
    public Customer create(Customer customer) {
        return customerEntityMapper.toModel(customerRepository.save(customerEntityMapper.toEntity(customer)));
    }

    @Override
    public Customer update(Customer customer) {
        CustomerEntity entity = customerRepository.save(customerEntityMapper.toEntity(customer));
        return customerEntityMapper.toModel(entity);
    }

    @Override
    public Customer findById(long customerId) {
        CustomerEntity entity = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException());
        return customerEntityMapper.toModel(entity);
    }

    @Override
    public boolean updateNextState(long customerId, String nextState) {
        return customerRepository.updateNextState(customerId, nextState) == 1;
    }
}
