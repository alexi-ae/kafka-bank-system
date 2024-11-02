package com.alexi.kafka.customer.command.application.service;

import com.alexi.kafka.customer.command.application.command.LoginCommand;
import com.alexi.kafka.customer.command.application.mapper.UserMapper;
import com.alexi.kafka.customer.command.application.usercase.AuthenticationService;
import com.alexi.kafka.customer.command.domain.dto.ResponseLoginDto;
import com.alexi.kafka.customer.command.domain.exception.ApiRestException;
import com.alexi.kafka.customer.command.domain.exception.ErrorReason;
import com.alexi.kafka.customer.command.domain.exception.ErrorSource;
import com.alexi.kafka.customer.command.domain.model.User;
import com.alexi.kafka.customer.command.domain.port.UserPersistencePort;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationManagementService implements AuthenticationService {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtService jwtService;

    @SneakyThrows
    @Override
    public ResponseLoginDto login(LoginCommand loginCommand) {
        authenticate(loginCommand.getEmail(), loginCommand.getPassword());
        User user = userPersistencePort.findByEmail(loginCommand.getEmail());
        String token = jwtService.generateToken(user);
        return ResponseLoginDto.builder()
                .jwttoken(token)
                .nextState(user.getCustomer().getNextState().name())
                .build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw ApiRestException.builder().reason(ErrorReason.AUTHENTICATION_FAILED)
                    .source(ErrorSource.BUSINESS_SERVICE).build();
        }
    }
}
