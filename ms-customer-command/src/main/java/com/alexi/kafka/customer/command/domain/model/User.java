package com.alexi.kafka.customer.command.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class User {
    private Long id;

    private String email;

    private String password;

    private Set<Rol> authorities;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Customer customer;
}
