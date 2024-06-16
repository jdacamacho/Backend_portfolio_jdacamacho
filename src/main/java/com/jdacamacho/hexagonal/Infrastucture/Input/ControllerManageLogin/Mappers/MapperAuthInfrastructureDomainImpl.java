package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Domain.Objects.Security.Credential;
import com.jdacamacho.hexagonal.Domain.Objects.Security.UserAuth;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTORequest.CredentialDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTOResponse.UserAuthDTOResponse;

@Service
public class MapperAuthInfrastructureDomainImpl implements MapperAuthInfrastructureDomainInt {
    private final ModelMapper mapper;

    public MapperAuthInfrastructureDomainImpl(ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public Credential mapRequestToModel(CredentialDTORequest request) {
        return this.mapper.map(request, Credential.class);
    }

    @Override
    public UserAuthDTOResponse mapModelToResponse(UserAuth model) {
        return this.mapper.map(model, UserAuthDTOResponse.class);
    }
}
