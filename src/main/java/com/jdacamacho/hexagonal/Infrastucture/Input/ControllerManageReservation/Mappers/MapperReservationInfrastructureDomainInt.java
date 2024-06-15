package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.Mappers;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Reservation;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.DTOResponse.ReservationDTOResponse;

public interface MapperReservationInfrastructureDomainInt {
    ReservationDTOResponse mapModelToResponse(Reservation model);
    List<ReservationDTOResponse> mapModelToResponse(List<Reservation> models);
}
