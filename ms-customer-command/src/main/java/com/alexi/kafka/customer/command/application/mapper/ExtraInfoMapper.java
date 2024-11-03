package com.alexi.kafka.customer.command.application.mapper;


import com.alexi.kafka.customer.command.application.command.CreateExtraInfoCommand;
import com.alexi.kafka.customer.command.domain.dto.ExtraInfoDto;
import com.alexi.kafka.customer.command.domain.model.ExtraInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExtraInfoMapper {

    ExtraInfo toModel(@MappingTarget ExtraInfo extraInfo, CreateExtraInfoCommand command);

    ExtraInfoDto toDto(ExtraInfo extraInfo);

    ExtraInfo fromPersonalInfo(@MappingTarget ExtraInfo extraInfo, CreateExtraInfoCommand request);
}
