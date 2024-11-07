package com.alexi.kafka.customer.command.domain.port;

import com.alexi.kafka.customer.command.domain.model.Customer;

public interface CustomerPersistencePort {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer findById(long customerId);

    boolean updateNextState(long customerId, String nextState);
}
