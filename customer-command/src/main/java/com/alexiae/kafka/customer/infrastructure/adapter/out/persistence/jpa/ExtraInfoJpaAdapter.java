package com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa;

import com.alexiae.kafka.customer.domain.model.ExtraInfo;
import com.alexiae.kafka.customer.domain.port.out.ExtraInfoPersistencePort;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.entity.ExtraInfoEntity;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.mapper.ExtraInfoEntityMapper;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.repository.ExtraInfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ExtraInfoJpaAdapter implements ExtraInfoPersistencePort {

    @Autowired
    private ExtraInfoRepository extraInfoRepository;

    @Autowired
    private ExtraInfoEntityMapper extraInfoEntityMapper;

    @Override
    public ExtraInfo create(ExtraInfo extraInfo) {
        ExtraInfoEntity extraInfoEntity = extraInfoRepository.save(extraInfoEntityMapper.toEntity(extraInfo));
        return extraInfoEntityMapper.toModel(extraInfoEntity);
    }
}
