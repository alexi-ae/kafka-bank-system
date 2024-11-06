package com.alexiae.kafka.auth.infrastructure.config;

import com.alexiae.kafka.auth.infrastructure.adapter.jpa.entity.RoleEntity;
import com.alexiae.kafka.auth.infrastructure.adapter.jpa.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) { // Verifica si hay registros
            roleRepository.save(new RoleEntity(null, "ADMIN"));
            roleRepository.save(new RoleEntity(null, "USER"));
            roleRepository.save(new RoleEntity(null, "CUSTOMER"));
        }
    }
}
