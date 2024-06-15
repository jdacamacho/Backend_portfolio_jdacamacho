package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Domain.Objects.Owner;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest.OwnerDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTOResponse.OwnerDTOResponse;

@Service
public class MapperOwnerInfrastructureDomainImpl implements MapperOwnerInfrastructureDomainInt{
    private final ModelMapper mapper;

    public MapperOwnerInfrastructureDomainImpl(ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public Owner mapRequestToModel(OwnerDTORequest request) {
        return this.mapper.map(request, Owner.class);
    }

    @Override
    public OwnerDTOResponse mapModelToResponse(Owner model) {
        return this.mapper.map(model, OwnerDTOResponse.class);
    }

    @Override
    public List<OwnerDTOResponse> mapModelToResponse(List<Owner> models) {
        return this.mapper.map(models, new TypeToken<List<OwnerDTOResponse>>(){}.getType());
    }


}
