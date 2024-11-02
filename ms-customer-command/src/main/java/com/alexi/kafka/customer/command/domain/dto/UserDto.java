package com.alexi.kafka.customer.command.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private Set<RolDto> authorities;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private CustomerDto customer;
}
