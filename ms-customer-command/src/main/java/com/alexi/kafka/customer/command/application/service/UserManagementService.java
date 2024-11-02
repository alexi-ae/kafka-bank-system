package com.alexi.kafka.customer.command.application.service;

import com.alexi.kafka.customer.command.application.command.CreateUserCommand;
import com.alexi.kafka.customer.command.application.mapper.UserMapper;
import com.alexi.kafka.customer.command.application.usercase.UserService;
import com.alexi.kafka.customer.command.domain.dto.UserDto;
import com.alexi.kafka.customer.command.domain.enums.CustomerStatus;
import com.alexi.kafka.customer.command.domain.enums.OnboardingStatus;
import com.alexi.kafka.customer.command.domain.exception.ApiRestException;
import com.alexi.kafka.customer.command.domain.exception.ErrorReason;
import com.alexi.kafka.customer.command.domain.exception.ErrorSource;
import com.alexi.kafka.customer.command.domain.model.Customer;
import com.alexi.kafka.customer.command.domain.model.User;
import com.alexi.kafka.customer.command.domain.port.CustomerPersistencePort;
import com.alexi.kafka.customer.command.domain.port.UserPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService implements UserService {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private CustomerPersistencePort customerPersistencePort;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto register(CreateUserCommand createUserCommand) {
        if (Boolean.TRUE.equals(userPersistencePort.existsByEmail(createUserCommand.getEmail()))) {
            throw ApiRestException.builder().reason(ErrorReason.USER_ALREADY_EXISTS).source(ErrorSource.BUSINESS_SERVICE).build();
        }
        var user = userPersistencePort.create(userMapper.toModel(createUserCommand));

        customerPersistencePort.create(toCustomer(user));
        return userMapper.toDto(user);
    }

    private Customer toCustomer(User user) {
        return Customer.builder().status(CustomerStatus.PENDING).nextState(OnboardingStatus.CONTACT).user(user).build();
    }
}
