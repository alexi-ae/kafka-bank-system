package com.alexiae.kafka.customer.command.domain.port;

import com.alexiae.kafka.customer.command.domain.model.ExtraInfo;

public interface ExtraInfoPersistencePort {

    ExtraInfo create(ExtraInfo extraInfo);
}
