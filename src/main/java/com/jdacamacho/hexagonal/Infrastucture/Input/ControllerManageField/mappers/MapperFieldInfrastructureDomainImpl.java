package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Domain.Objects.Field;
import com.jdacamacho.hexagonal.Domain.Objects.Schedule;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTORequest.FieldDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.FieldDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.ScheduleDTOResponse;

@Service
public class MapperFieldInfrastructureDomainImpl implements MapperFieldInfrastructureDomainInt{
    private final ModelMapper mapper;

    public MapperFieldInfrastructureDomainImpl(ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public Field mapRequestToModel(FieldDTORequest request) {
        return this.mapper.map(request, Field.class);
    }

    @Override
    public FieldDTOResponse mapModelToResponse(Field model) {
        return this.mapper.map(model, FieldDTOResponse.class);
    }

    @Override
    public List<FieldDTOResponse> mapModelToResponse(List<Field> models) {
        return this.mapper.map(models, new TypeToken<List<FieldDTOResponse>>(){}.getType());
    }

    @Override
    public List<ScheduleDTOResponse> mapModelsToResponse(List<Schedule> models) {
        return this.mapper.map(models, new TypeToken<List<ScheduleDTOResponse>>(){}.getType());
    }

    

}
