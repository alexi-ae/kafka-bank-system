package com.alexi.kafka.customer.command.domain.model;

import com.alexi.kafka.customer.command.domain.enums.FileType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Files {
    private Long id;

    private FileType type;

    private String imagePath;

    private Customer customer;
}
