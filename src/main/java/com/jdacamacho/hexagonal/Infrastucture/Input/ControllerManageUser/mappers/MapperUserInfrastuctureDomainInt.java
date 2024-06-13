package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.mappers;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.User;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

public interface MapperUserInfrasctuctureDomainInt {
    User mapRequestToModel(UserDTORequest request);
    UserDTOResponse mapModelToResponse(User model);
    List<UserDTOResponse> mapModelToResponse(List<User> models);
}
