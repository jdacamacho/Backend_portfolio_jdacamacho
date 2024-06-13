package com.jdacamacho.hexagonal.Application.Input;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Models.Owner;

public interface ManageOwnerCUIntPort {
    public List<Owner> listOwners();
    public Owner saverOwner(Owner owner);
    public Owner updateOwner(long id, Owner owner);
    public Owner findById(long id);
}
