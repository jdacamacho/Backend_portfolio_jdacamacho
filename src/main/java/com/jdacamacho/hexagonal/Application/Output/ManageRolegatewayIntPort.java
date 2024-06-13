package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Models.Role;

public interface ManageRolegatewayIntPort {
    List<Role> findAll();
}
