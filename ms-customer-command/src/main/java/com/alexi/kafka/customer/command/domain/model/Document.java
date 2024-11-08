package com.alexi.kafka.customer.command.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Document {
    private Long id;
    private String country;
    private String type;
    private String number;
}
