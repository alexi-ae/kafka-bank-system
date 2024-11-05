package com.alexiae.kafka.auth.infrastructure.adapter.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tokens")
public class TokenEntity {
    @Id
    private String id;
    private String userId;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean revoked;
}
