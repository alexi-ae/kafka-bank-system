package com.alexi.kafka.customer.command.infrastructure.adapter;

import com.alexi.kafka.customer.command.domain.model.Document;
import com.alexi.kafka.customer.command.domain.port.DocumentPersistencePort;
import com.alexi.kafka.customer.command.infrastructure.adapter.entity.DocumentEntity;
import com.alexi.kafka.customer.command.infrastructure.adapter.mapper.DocumentEntityMapper;
import com.alexi.kafka.customer.command.infrastructure.adapter.repository.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DocumentJpaAdapter implements DocumentPersistencePort {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentEntityMapper documentEntityMapper;

    @Override
    public Document create(Document document) {
        DocumentEntity documentEntity = documentRepository.save(documentEntityMapper.toEntity(document));
        return documentEntityMapper.toModel(documentEntity);
    }
}
