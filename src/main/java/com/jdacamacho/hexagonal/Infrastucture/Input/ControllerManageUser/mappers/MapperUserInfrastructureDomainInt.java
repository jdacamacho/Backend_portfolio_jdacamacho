package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.mappers;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserUpdateDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

public interface MapperUserInfrastructureDomainInt {
    User mapRequestToModel(UserDTORequest request);
    User mapRequestToModel(UserUpdateDTORequest request);
    UserDTOResponse mapModelToResponse(User model);
    List<UserDTOResponse> mapModelToResponse(List<User> models);
}
