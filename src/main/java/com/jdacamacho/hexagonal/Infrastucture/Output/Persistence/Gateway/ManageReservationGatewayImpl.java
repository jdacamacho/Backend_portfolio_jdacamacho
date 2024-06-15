package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageReservationGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Reservation;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.ReservationEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.ReservationRepository;

@Service
public class ManageReservationGatewayImpl implements ManageReservationGatewayIntPort {
    private final ReservationRepository serviceBD;
    private final ModelMapper mapper;

    public ManageReservationGatewayImpl(ReservationRepository serviceBD,
                                        ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
    }

    @Override
    public List<Reservation> findAll() {
        List<ReservationEntity> data = this.serviceBD.findAll();
        List<Reservation> response = this.mapper.map(data, new TypeToken<List<Reservation>>(){}.getType());
        return response;
    }

    @Override
    public Reservation save(Reservation reservation) {
        ReservationEntity reservationToSave = this.mapper.map(reservation, ReservationEntity.class);
        ReservationEntity reservationSaved = this.serviceBD.save(reservationToSave);
        Reservation response = this.mapper.map(reservationSaved, Reservation.class);
        return response;
    }

    @Override
    public void delete(Reservation reservation) {
        ReservationEntity reservationToDelete = this.mapper.map(reservation, ReservationEntity.class);
        this.serviceBD.delete(reservationToDelete);
    }

    @Override
    public boolean existsById(long id) {
        return this.serviceBD.existsById(id);
    }

    @Override
    public Reservation findById(long id) {
        ReservationEntity data = this.serviceBD.findById(id).get();
        Reservation response = this.mapper.map(data, Reservation.class);
        return response;
    }
}
