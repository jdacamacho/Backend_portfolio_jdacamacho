package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.mappers;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Field;
import com.jdacamacho.hexagonal.Domain.Objects.Schedule;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTORequest.FieldDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.FieldDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.ScheduleDTOResponse;

public interface MapperFieldInfrastructureDomainInt {
    Field mapRequestToModel(FieldDTORequest request);
    FieldDTOResponse mapModelToResponse(Field model);
    List<FieldDTOResponse> mapModelToResponse(List<Field> models);
    List<ScheduleDTOResponse> mapModelsToResponse(List<Schedule> models);
}
