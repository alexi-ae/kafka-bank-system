package com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @DBRef
    private List<RoleEntity> roles;

    private Long customerId;
}
