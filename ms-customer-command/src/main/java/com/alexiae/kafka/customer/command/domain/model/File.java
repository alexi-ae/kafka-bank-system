package com.alexiae.kafka.customer.command.domain.model;

import com.alexiae.kafka.customer.command.domain.enums.FileType;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private Long id;

    private FileType type;

    private String imagePath;

    private Customer customer;
}
