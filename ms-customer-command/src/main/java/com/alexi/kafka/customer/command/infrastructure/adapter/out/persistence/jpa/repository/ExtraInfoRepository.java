package com.alexi.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.repository;

import com.alexi.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.ExtraInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraInfoRepository extends JpaRepository<ExtraInfoEntity, Long> {
}
