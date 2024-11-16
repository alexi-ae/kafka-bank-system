package com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa;

import com.alexiae.kafka.transaction.domain.model.Transaction;
import com.alexiae.kafka.transaction.domain.port.TransactionPersistencePort;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.entity.TransactionEntity;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.mapper.TransactionEntityMapper;
import com.alexiae.kafka.transaction.infrastructure.adapter.out.persistence.jpa.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionJpaAdapter implements TransactionPersistencePort {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionEntityMapper transactionEntityMapper;

    @Override
    public Transaction create(Transaction model) {
        TransactionEntity entity = transactionEntityMapper.toEntity(model);
        return transactionEntityMapper.toModel(transactionRepository.save(entity));
    }

    @Override
    public Transaction getById(Long id) {
        return transactionRepository.findById(id).map(transactionEntityMapper::toModel).orElse(null);
    }

    @Override
    public Transaction update(Transaction model) {
        TransactionEntity entity = transactionEntityMapper.toEntity(model);
        entity = transactionRepository.save(entity);
        return transactionEntityMapper.toModel(entity);
    }
}
