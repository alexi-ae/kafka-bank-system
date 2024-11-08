package com.alexi.kafka.customer.command.infrastructure.adapter.jpa;

import com.alexi.kafka.customer.command.domain.model.File;
import com.alexi.kafka.customer.command.domain.port.FilePersistencePort;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.FilesEntity;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.mapper.FileEntityMapper;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository.FileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FileJpaAdapter implements FilePersistencePort {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileEntityMapper fileEntityMapper;

    @Override
    public File create(File files) {
        FilesEntity filesEntity = fileRepository.save(fileEntityMapper.toEntity(files));
        return fileEntityMapper.toModel(filesEntity);
    }
}
