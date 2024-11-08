package com.alexiae.kafka.customer.command.application.mapper;


import com.alexiae.kafka.customer.command.application.command.CreateExtraInfoCommand;
import com.alexiae.kafka.customer.command.domain.dto.ExtraInfoDto;
import com.alexiae.kafka.customer.command.domain.model.ExtraInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExtraInfoMapper {

    ExtraInfo toModel(@MappingTarget ExtraInfo extraInfo, CreateExtraInfoCommand command);

    ExtraInfoDto toDto(ExtraInfo extraInfo);

    ExtraInfo fromPersonalInfo(@MappingTarget ExtraInfo extraInfo, CreateExtraInfoCommand request);
}
