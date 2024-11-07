package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findCustomerEntityByUserEmail(String email);

    boolean existsCustomerEntityByUserEmail(String email);

    @Modifying
    @Query(value = "UPDATE customers SET next_state = :nextState WHERE id = :customerId", nativeQuery = true)
    int updateNextState(@Param("customerId") Long customerId, @Param("nextState") String nextState);
}
