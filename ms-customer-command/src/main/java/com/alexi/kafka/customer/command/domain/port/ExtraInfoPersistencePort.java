package com.alexi.kafka.customer.command.domain.port;

import com.alexi.kafka.customer.command.domain.model.ExtraInfo;

public interface ExtraInfoPersistencePort {

    ExtraInfo create(ExtraInfo extraInfo);
}
