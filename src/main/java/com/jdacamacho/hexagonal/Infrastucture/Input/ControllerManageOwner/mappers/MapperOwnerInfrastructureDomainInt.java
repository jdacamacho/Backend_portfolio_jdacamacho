package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.mappers;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Owner;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest.OwnerDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTOResponse.OwnerDTOResponse;

public interface MapperOwnerInfrastructureDomainInt {
    Owner mapRequestToModel(OwnerDTORequest request);
    OwnerDTOResponse mapModelToResponse(Owner model);
    List<OwnerDTOResponse> mapModelToResponse(List<Owner> models);
}
