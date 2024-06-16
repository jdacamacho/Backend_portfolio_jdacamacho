package com.jdacamacho.hexagonal.Application.Output;

import com.jdacamacho.hexagonal.Domain.Objects.User;

public interface ManageAuthenticatorGatewayIntPort {
    public boolean existsByUsername(String username);
    public String authenticateUser(String username, String password);
    public User findByUsername(String username);
}
