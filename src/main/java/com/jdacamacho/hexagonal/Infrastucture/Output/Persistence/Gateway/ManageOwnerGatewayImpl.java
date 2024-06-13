package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageOwnerGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Models.Owner;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.OwnerEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.OwnerRepository;

@Service
public class ManageOwnerGatewayImpl implements ManageOwnerGatewayIntPort{
    private final OwnerRepository serviceBD;
    private final ModelMapper mapper;

    public ManageOwnerGatewayImpl(OwnerRepository serviceBD, 
                                ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
    }


    @Override
    public List<Owner> findAll() {
        Iterable<OwnerEntity> data = this.serviceBD.findAll();
        List<Owner> response = this.mapper.map(data, new TypeToken<List<Owner>>(){}.getType());
        return response;
    }

    @Override
    public Owner save(Owner owner) {
        OwnerEntity ownerToSave = this.mapper.map(owner, OwnerEntity.class);
        OwnerEntity ownerSaved = this.serviceBD.save(ownerToSave);
        Owner response = this.mapper.map(ownerSaved, Owner.class);
        return response;
    }

    @Override
    public Owner findById(long id) {
        OwnerEntity data = this.serviceBD.findById(id).get();
        Owner response = this.mapper.map(data, Owner.class);
        return response;
    }

    @Override
    public boolean existsById(long id) {
        return this.serviceBD.existsById(id);
    }

    @Override
    public boolean existsByDocumentNumber(long documentNumber) {
        return this.serviceBD.existsByDocumentNumber(documentNumber);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.serviceBD.existsByUsername(username);
    }

    @Override
    public boolean existsPropertyName(String propertyName) {
        return this.existsPropertyName(propertyName);
    }

    @Override
    public boolean existsByNit(long nit) {
        return this.existsByNit(nit);
    }
    
}
