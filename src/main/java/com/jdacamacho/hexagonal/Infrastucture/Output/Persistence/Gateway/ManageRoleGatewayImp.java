package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageRolegatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Role;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.RoleEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.RoleRepository;

@Service
public class ManageRoleGatewayImp implements ManageRolegatewayIntPort{
    private final RoleRepository serviceBD;
    private final ModelMapper mapper;

    public ManageRoleGatewayImp(RoleRepository serviceBD,
                            ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
    }

    @Override
    public List<Role> findAll() {
        Iterable<RoleEntity> data = this.serviceBD.findAll();
        List<Role> response = this.mapper.map(data, new TypeToken<List<Role>>(){}.getType());
        return response;
    }

    @Override
    public List<Role> findAllExceptAdm() {
        Iterable<RoleEntity> data = this.serviceBD.findAllExceptAdministrator();
        List<Role> response = this.mapper.map(data, new TypeToken<List<Role>>(){}.getType());
        return response;
    }
    
}
