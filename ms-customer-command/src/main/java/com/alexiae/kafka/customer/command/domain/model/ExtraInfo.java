package com.alexiae.kafka.customer.command.domain.model;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtraInfo {

    private Long id;
    private boolean politicallyExposed;
    private String maritalStatus;
    private String documentType;
    private String documentNumber;
    private String documentCountry;
}
