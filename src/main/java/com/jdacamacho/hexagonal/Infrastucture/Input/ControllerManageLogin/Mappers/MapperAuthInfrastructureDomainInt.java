package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.Mappers;

import com.jdacamacho.hexagonal.Domain.Objects.Security.Credential;
import com.jdacamacho.hexagonal.Domain.Objects.Security.UserAuth;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTORequest.CredentialDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTOResponse.UserAuthDTOResponse;

public interface MapperAuthInfrastructureDomainInt {
    Credential mapRequestToModel(CredentialDTORequest request);
    UserAuthDTOResponse mapModelToResponse(UserAuth model);
}
