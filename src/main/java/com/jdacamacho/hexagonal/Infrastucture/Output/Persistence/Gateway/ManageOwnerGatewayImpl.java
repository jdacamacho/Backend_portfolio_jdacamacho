package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageOwnerGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Owner;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.OwnerEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.OwnerRepository;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.UserRepository;

@Service
public class ManageOwnerGatewayImpl implements ManageOwnerGatewayIntPort{
    private final OwnerRepository serviceBDOwner;
    private final UserRepository serviceBDUser;
    private final ModelMapper mapper;

    public ManageOwnerGatewayImpl(OwnerRepository serviceBDOwner, 
                                UserRepository serviceBDUser,
                                ModelMapper mapper){
        this.serviceBDOwner = serviceBDOwner;
        this.serviceBDUser = serviceBDUser;
        this.mapper = mapper;
    }


    @Override
    public List<Owner> findAll() {
        Iterable<OwnerEntity> data = this.serviceBDOwner.findAll();
        List<Owner> response = this.mapper.map(data, new TypeToken<List<Owner>>(){}.getType());
        return response;
    }

    @Override
    public Owner save(Owner owner) {
        OwnerEntity ownerToSave = this.mapper.map(owner, OwnerEntity.class);
        OwnerEntity ownerSaved = this.serviceBDOwner.save(ownerToSave);
        Owner response = this.mapper.map(ownerSaved, Owner.class);
        return response;
    }

    @Override
    public Owner findById(long id) {
        OwnerEntity data = this.serviceBDOwner.findById(id).get();
        Owner response = this.mapper.map(data, Owner.class);
        return response;
    }

    @Override
    public boolean existsById(long id) {
        return this.serviceBDOwner.existsById(id);
    }

    @Override
    public boolean existsByDocumentNumber(long documentNumber) {
        return this.serviceBDUser.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.serviceBDUser.existsByUsername(username);
    }

    @Override
    public boolean existsPropertyName(String propertyName) {
        return this.serviceBDOwner.existsByPropertyName(propertyName);
    }

    @Override
    public boolean existsByNit(long nit) {
        return this.serviceBDOwner.existsByNit(nit);
    }
    
}
