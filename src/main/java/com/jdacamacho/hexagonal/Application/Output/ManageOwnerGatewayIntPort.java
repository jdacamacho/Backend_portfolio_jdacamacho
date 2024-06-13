package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Owner;

public interface ManageOwnerGatewayIntPort {
    public List<Owner> findAll();
    public Owner save(Owner owner);
    public Owner findById(long id);
    public boolean existsById(long id);
    public boolean existsByDocumentNumber(long documentNumber);
    public boolean existsByUsername(String username);
    public boolean existsPropertyName(String propertyName);
    public boolean existsByNit(long nit);
}
