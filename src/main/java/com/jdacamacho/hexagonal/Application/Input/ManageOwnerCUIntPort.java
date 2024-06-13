package com.jdacamacho.hexagonal.Application.Input;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Owner;

public interface ManageOwnerCUIntPort {
    public List<Owner> listOwners();
    public Owner saverOwner(Owner owner);
    public Owner updateOwner(long id, Owner owner);
    public Owner findOwnerById(long id);
}
