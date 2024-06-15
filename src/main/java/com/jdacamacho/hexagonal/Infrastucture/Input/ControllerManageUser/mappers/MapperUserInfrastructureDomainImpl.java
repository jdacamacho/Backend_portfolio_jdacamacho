package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

@Service
public class MapperUserInfrastuctureDomainImpl implements MapperUserInfrastuctureDomainInt {
    private final ModelMapper mapper;

    public MapperUserInfrastuctureDomainImpl(ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public User mapRequestToModel(UserDTORequest request) {
        return this.mapper.map(request, User.class);
    }

    @Override
    public UserDTOResponse mapModelToResponse(User model) {
        return this.mapper.map(model, UserDTOResponse.class);
    }

    @Override
    public List<UserDTOResponse> mapModelToResponse(List<User> models) {
        return this.mapper.map(models, new TypeToken<List<UserDTOResponse>>(){}.getType());
    }

}
