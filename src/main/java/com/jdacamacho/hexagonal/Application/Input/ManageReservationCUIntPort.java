package com.jdacamacho.hexagonal.Application.Input;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Reservation;

public interface ManageReservationCUIntPort {
    public List<Reservation> listReservations();
    public List<Reservation> listReservationByUserName(String name);
    public List<Reservation> listReservationByUserId(long idUser);
    public Reservation makeReservation(long idUser, long idSchedule, Reservation reservation);
    public boolean cancelReservation(long idReservation);
    public Reservation findReservationById(long idReservation);

}