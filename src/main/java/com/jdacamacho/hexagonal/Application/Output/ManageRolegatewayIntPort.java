package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Role;

public interface ManageRolegatewayIntPort {
    List<Role> findAll();
    List<Role> findAllExceptAdm();
}
