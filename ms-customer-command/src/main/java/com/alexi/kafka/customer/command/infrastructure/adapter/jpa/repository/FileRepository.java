package com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FilesEntity, Long> {
}
