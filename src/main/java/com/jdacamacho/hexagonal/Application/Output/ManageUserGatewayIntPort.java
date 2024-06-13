package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Models.User;

public interface ManageUserGatewayIntPort {
    public List<User> findAll();
    public User save(User user);
    public User findById(long id);
    public boolean existsById(long id);
    public boolean existsByDocumentNumber(long documentNumber);
    public boolean existsByUsername(String username);
}
