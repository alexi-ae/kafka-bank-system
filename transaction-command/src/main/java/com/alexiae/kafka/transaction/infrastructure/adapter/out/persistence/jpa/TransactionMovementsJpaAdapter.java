package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa;

import com.alexiae.kafka.transaction.domain.model.TransactionMovement;
import com.alexiae.kafka.transaction.domain.port.TransactionMovementsPersistencePort;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity.TransactionMovementEntity;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.mapper.TransactionMovementEntityMapper;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.repository.TransactionMovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionMovementsJpaAdapter implements TransactionMovementsPersistencePort {
    @Autowired
    private TransactionMovementsRepository transactionMovementsRepository;

    @Autowired
    private TransactionMovementEntityMapper transactionMovementEntityMapper;

    @Override
    public TransactionMovement create(TransactionMovement model) {
        TransactionMovementEntity entity = transactionMovementEntityMapper.toEntity(model);
        return transactionMovementEntityMapper.toModel(transactionMovementsRepository.save(entity));
    }
}
