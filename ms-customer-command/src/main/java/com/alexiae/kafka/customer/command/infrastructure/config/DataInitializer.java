package com.alexiae.kafka.customer.command.infrastructure.config;

import com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.entity.RolEntity;
import com.alexiae.kafka.customer.command.infrastructure.adapter.out.persistence.jpa.repository.RolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RolRepository rolRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (rolRepository.count() == 0) { // Verifica si hay registros
            rolRepository.save(new RolEntity(null, "ADMIN"));
            rolRepository.save(new RolEntity(null, "USER"));
            rolRepository.save(new RolEntity(null, "CUSTOMER"));
        }
    }
}