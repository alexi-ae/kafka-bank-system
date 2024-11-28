package com.alexiae.kafka.customer.domain.port.in;

import com.alexiae.kafka.customer.domain.model.ObnInfo;

public interface OnboardingUseCase {

    ObnInfo process(long customerId);
}
