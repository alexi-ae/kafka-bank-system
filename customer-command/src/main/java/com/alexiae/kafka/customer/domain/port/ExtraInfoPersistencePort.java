package com.alexiae.kafka.customer.domain.port;

import com.alexiae.kafka.customer.domain.model.ExtraInfo;

public interface ExtraInfoPersistencePort {

    ExtraInfo create(ExtraInfo extraInfo);
}
