package com.alexiae.kafka.auth.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    private String id;
    private String description;
}
