package com.alexiae.kafka.auth.infrastructure.adapter.jpa;

import com.alexiae.kafka.auth.domain.exception.ApiRestException;
import com.alexiae.kafka.auth.domain.exception.ErrorReason;
import com.alexiae.kafka.auth.domain.exception.ErrorSource;
import com.alexiae.kafka.auth.domain.model.User;
import com.alexiae.kafka.auth.domain.port.UserPersistencePort;
import com.alexiae.kafka.auth.infrastructure.adapter.jpa.entity.RoleEntity;
import com.alexiae.kafka.auth.infrastructure.adapter.jpa.entity.UserEntity;
import com.alexiae.kafka.auth.infrastructure.adapter.jpa.mapper.UserEntityMapper;
import com.alexiae.kafka.auth.infrastructure.adapter.jpa.repository.RoleRepository;
import com.alexiae.kafka.auth.infrastructure.adapter.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@Transactional
public class UserJpaAdapter implements UserPersistencePort {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Override
    public User create(User model) {
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            throw ApiRestException.builder()
                    .reason(ErrorReason.USER_ALREADY_EXISTS)
                    .source(ErrorSource.BUSINESS_SERVICE)
                    .build();
        }

        model.setPassword(passwordEncoder.encode(model.getPassword()));
        model.setRoles(new HashSet<>());
        UserEntity userEntity = userEntityMapper.toEntity(model);
        RoleEntity rolEntity = roleRepository.findRolEntityByDescription("USER").orElseThrow(() -> new RuntimeException("NO HAS ROLE"));
        userEntity.getRoles().add(rolEntity);

        return userEntityMapper.toModel(userRepository.save(userEntity));
    }

    @Override
    public User getByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Erro encontrando el user"));
        return userEntityMapper.toModel(entity);
    }
}
