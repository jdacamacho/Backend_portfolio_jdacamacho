package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.MapperInfrastuctureDomain;

import java.util.List;

import org.mapstruct.Mapper;

import com.jdacamacho.hexagonal.Domain.Models.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

@Mapper(componentModel = "spring")
public interface MapperUserInfrasctuctureDomain {
    User mapRequestToModel(UserDTORequest request);
    UserDTOResponse mapModelToResponse(User model);
    List<UserDTOResponse> mapModelsToResponse(List<User> models);
}
