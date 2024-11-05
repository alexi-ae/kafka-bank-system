package com.alexiae.kafka.auth.domain.model;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Token {
    private String id;
    private String userId;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean revoked;
}
