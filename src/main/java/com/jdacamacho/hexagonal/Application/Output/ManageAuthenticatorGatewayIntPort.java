package com.jdacamacho.hexagonal.Application.Output;

import com.jdacamacho.hexagonal.Domain.Objects.User;

public interface ManageAuthenticatorGatewayIntPort {
    public boolean authenticationIsValid(String username, String password);
    public String authenticateUser(String username, String password);
    public User findByUsername(String username);
}
