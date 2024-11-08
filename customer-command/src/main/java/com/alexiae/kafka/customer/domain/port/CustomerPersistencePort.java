package com.alexiae.kafka.customer.domain.port;

import com.alexiae.kafka.customer.domain.model.Customer;

public interface CustomerPersistencePort {

    Customer create(Customer customer);

    Customer update(Customer customer);

    Customer findById(long customerId);

    boolean updateNextState(long customerId, String nextState);
}
