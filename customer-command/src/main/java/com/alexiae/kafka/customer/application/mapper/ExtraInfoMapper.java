package com.alexiae.kafka.customer.application.mapper;


import com.alexiae.kafka.customer.application.command.CreateExtraInfoCommand;
import com.alexiae.kafka.customer.domain.dto.ExtraInfoDto;
import com.alexiae.kafka.customer.domain.model.ExtraInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExtraInfoMapper {

    ExtraInfo toModel(@MappingTarget ExtraInfo extraInfo, CreateExtraInfoCommand command);

    ExtraInfoDto toDto(ExtraInfo extraInfo);

    ExtraInfo fromPersonalInfo(@MappingTarget ExtraInfo extraInfo, CreateExtraInfoCommand request);
}
