package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.Mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Domain.Objects.Reservation;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.DTOResponse.ReservationDTOResponse;

@Service
public class MapperReservationInfrastructureDomainImpl implements MapperReservationInfrastructureDomainInt{
    private final ModelMapper mapper;

    public MapperReservationInfrastructureDomainImpl(ModelMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public ReservationDTOResponse mapModelToResponse(Reservation model) {
        return this.mapper.map(model, ReservationDTOResponse.class);
    }

    @Override
    public List<ReservationDTOResponse> mapModelToResponse(List<Reservation> models) {
        return this.mapper.map(models, new TypeToken<List<ReservationDTOResponse>>(){}.getType());
    }


}
