package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Reservation;

public interface ManageReservationGatewayIntPort {
    public List<Reservation> findAll();
    public Reservation save(Reservation reservation);
    public void delete(Reservation reservation);
    public boolean existsById(long id);
    public Reservation findById(long id);

}
