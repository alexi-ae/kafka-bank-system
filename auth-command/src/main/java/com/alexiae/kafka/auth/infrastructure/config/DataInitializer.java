package com.alexiae.kafka.auth.infrastructure.config;

import com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.entity.RoleEntity;
import com.alexiae.kafka.auth.infrastructure.adapter.out.persistence.jpa.repository.RoleRepository;
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

        try {
            if (roleRepository.count() == 0) { // Verifica si hay registros
                roleRepository.save(new RoleEntity(null, "ADMIN"));
                roleRepository.save(new RoleEntity(null, "USER"));
                roleRepository.save(new RoleEntity(null, "CUSTOMER"));
            }
        } catch (Exception e) {
            System.out.println("No se pudo inicializar los datos por conectividad.");
        }
    }
}
