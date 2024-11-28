package com.alexiae.kafka.customer.application.usecases;

import com.alexiae.kafka.customer.application.mapper.ExtraInfoMapper;
import com.alexiae.kafka.customer.domain.command.CreateExtraInfoCommand;
import com.alexiae.kafka.customer.domain.enums.OnboardingStatus;
import com.alexiae.kafka.customer.domain.model.Customer;
import com.alexiae.kafka.customer.domain.model.ExtraInfo;
import com.alexiae.kafka.customer.domain.model.ObnInfo;
import com.alexiae.kafka.customer.domain.port.in.ManageExtraDataUseCase;
import com.alexiae.kafka.customer.domain.port.out.CustomerPersistencePort;
import com.alexiae.kafka.customer.domain.port.out.ExtraInfoPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ManageExtraDataUseCaseImpl implements ManageExtraDataUseCase {

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private ExtraInfoPersistencePort extraInfoPersistencePort;

    @Autowired
    private ExtraInfoMapper extraInfoMapper;

    @Override
    public ObnInfo save(CreateExtraInfoCommand request, long customerId) {
        Customer customer = customerPersistencePort.findById(customerId);
        customer.setExtraInfo(Objects.isNull(customer.getExtraInfo()) ? new ExtraInfo() : customer.getExtraInfo());

        ExtraInfo extraInfo = extraInfoMapper.toModel(customer.getExtraInfo(), request);
        extraInfo = extraInfoPersistencePort.create(extraInfo);

        customer.setExtraInfo(extraInfo);
        customer.setNextState(OnboardingStatus.PROCESSING);
        customer = customerPersistencePort.update(customer);
        return ObnInfo.builder().nextState(customer.getNextState().name()).build();
    }
}
