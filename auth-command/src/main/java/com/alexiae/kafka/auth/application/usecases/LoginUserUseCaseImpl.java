package com.alexiae.kafka.auth.application.usecases;

import com.alexiae.kafka.auth.domain.command.LoginCommand;
import com.alexiae.kafka.auth.domain.exception.ApiRestException;
import com.alexiae.kafka.auth.domain.exception.ErrorReason;
import com.alexiae.kafka.auth.domain.exception.ErrorSource;
import com.alexiae.kafka.auth.domain.model.AuthToken;
import com.alexiae.kafka.auth.domain.model.User;
import com.alexiae.kafka.auth.domain.port.in.LoginUserUseCase;
import com.alexiae.kafka.auth.domain.port.out.TokenPort;
import com.alexiae.kafka.auth.domain.port.out.UserPersistencePort;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginUserUseCaseImpl implements LoginUserUseCase {

    @Autowired
    private UserPersistencePort userPersistencePort;

    @Autowired
    private TokenPort tokenPort;

    @Autowired
    private AuthenticationManager authenticationManager;


    @SneakyThrows
    @Override
    public AuthToken execute(LoginCommand command) {
        authenticate(command.getEmail(), command.getPassword());
        User user = userPersistencePort.getByEmail(command.getEmail());
        String tokenString = tokenPort.generate(user);
        return AuthToken.builder()
                .token(tokenString)
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
