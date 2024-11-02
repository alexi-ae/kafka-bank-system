package com.alexi.kafka.customer.command.application.service;

import com.alexi.kafka.customer.command.domain.model.User;

public interface JwtService {

    String generateToken(User user);
}
