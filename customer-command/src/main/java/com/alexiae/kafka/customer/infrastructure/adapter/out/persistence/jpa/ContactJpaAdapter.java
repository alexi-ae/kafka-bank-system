package com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa;

import com.alexiae.kafka.customer.domain.model.Contact;
import com.alexiae.kafka.customer.domain.port.out.ContactPersistencePort;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.entity.ContactEntity;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.mapper.ContactEntityMapper;
import com.alexiae.kafka.customer.infrastructure.adapter.out.persistence.jpa.repository.ContactRepository;
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
