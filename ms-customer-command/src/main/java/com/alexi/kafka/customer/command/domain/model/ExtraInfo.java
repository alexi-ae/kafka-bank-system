package com.alexi.kafka.customer.command.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraInfo {
    private Long id;

    private boolean politicallyExposed;
}
