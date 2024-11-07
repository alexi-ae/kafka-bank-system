package com.alexi.kafka.customer.command.infrastructure.adapter.jpa;

import com.alexi.kafka.customer.command.domain.model.User;
import com.alexi.kafka.customer.command.domain.port.UserPersistencePort;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.RolEntity;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.entity.UserEntity;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.mapper.UserEntityMapper;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository.RolRepository;
import com.alexi.kafka.customer.command.infrastructure.adapter.jpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@Transactional
public class UserJpaAdapter implements UserPersistencePort {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(new HashSet<>());
        UserEntity userEntity = userEntityMapper.toEntity(user);

        RolEntity rolEntity = rolRepository.findRolEntityByDescription("USER").orElseThrow(() -> new RuntimeException(""));
        userEntity.getAuthorities().add(rolEntity);

        return userEntityMapper.toModel(userRepository.save(userEntity));
    }

    @Override
    public User findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Erro encontrando el user"));
        return userEntityMapper.toModel(userEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsUserEntityByEmail(email);
    }
}
