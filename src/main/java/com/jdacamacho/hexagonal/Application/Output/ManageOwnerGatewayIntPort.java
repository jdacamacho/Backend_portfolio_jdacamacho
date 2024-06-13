package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Models.Owner;

public interface ManageOwnerGatewayIntPort {
    public List<Owner> findAll();
    public Owner save(Owner owner);
    public Owner findById(long documentNumber);
    public boolean existsById(long documentNumber);
    public boolean existsByUsername(String username);
    public boolean findByPropertyName(String propertyName);
    public boolean findByNit(long nit);
}
