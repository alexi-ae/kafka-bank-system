package com.alexiae.kafka.auth.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class User {
    private String id;

    private String email;

    private String password;

    private List<Role> roles;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long customerId;

    private String status;
}
