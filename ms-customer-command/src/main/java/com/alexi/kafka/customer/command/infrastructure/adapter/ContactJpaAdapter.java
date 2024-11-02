package com.alexi.kafka.customer.command.infrastructure.adapter;

import com.alexi.kafka.customer.command.domain.model.Contact;
import com.alexi.kafka.customer.command.domain.port.ContactPersistencePort;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.ContactEntity;
import com.alexi.kafka.customer.command.infrastructure.adapter.mapper.ContactEntityMapper;
import com.alexi.kafka.customer.command.infrastructure.adapter.repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContactJpaAdapter implements ContactPersistencePort {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactEntityMapper contactEntityMapper;

    @Override
    public Contact create(Contact contact) {
        ContactEntity contactEntity = contactRepository.save(contactEntityMapper.toEntity(contact));
        return contactEntityMapper.toModel(contactEntity);
    }
}
