package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.List;
import java.util.stream.Collectors;

import com.jdacamacho.hexagonal.Application.Input.ManageReservationCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageFieldGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageReservationGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Reservation;
import com.jdacamacho.hexagonal.Domain.Objects.User;

public class ManageReservationCUImplAdapter implements ManageReservationCUIntPort {
    private final ManageReservationGatewayIntPort gatewayReservation;
    private final ManageUserGatewayIntPort gatewayUser;
    private final ManageFieldGatewayIntPort gatewayField;
    private final ExceptionFormatterIntPort exceptionFormatter;
    
    public ManageReservationCUImplAdapter(ManageReservationGatewayIntPort gatewayReservation,
                                        ManageUserGatewayIntPort gatewayUser,
                                        ManageFieldGatewayIntPort gatewayField,
                                        ExceptionFormatterIntPort exceptionFormatter){
        this.gatewayReservation = gatewayReservation;
        this.gatewayUser = gatewayUser;
        this.gatewayField = gatewayField;
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public List<Reservation> listReservations() {
        List<Reservation> reservations = this.gatewayReservation.findAll();
        
        if(reservations.isEmpty()){
            this.exceptionFormatter.responseNoData("There are no reservations...");
        }
        
        return reservations;
    }

    @Override
    public List<Reservation> listReservationByUserName(String name) {
        
        List<User> users = this.gatewayUser.findAllByName(name);

        if(users.isEmpty()){
            this.exceptionFormatter.responseEntityNotFound("User was not found...");
        }

        return users.stream()
                            .flatMap(user -> user.getReservations().stream())
                            .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> listReservationByUserId(long idUser) {

        if(!this.gatewayUser.existsById(idUser)){
            this.exceptionFormatter.responseEntityNotFound("User was not found...");
        }

        User objUser = this.gatewayUser.findById(idUser);

        return objUser.getReservations();
    }

    @Override
    public Reservation makeReservation(long idUser, long idSchedule, Reservation reservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeReservation'");
    }

    @Override
    public boolean cancelReservation(long idReservation) {
        
        if(!this.gatewayReservation.existsById(idReservation)){
            this.exceptionFormatter.responseEntityNotFound("Reservation was not found...");
        }

        Reservation objReservation = this.gatewayReservation.findById(idReservation);

        this.gatewayReservation.delete(objReservation);

        return true;
    }

    @Override
    public Reservation findReservationById(long idReservation) {
        
        if(!this.gatewayReservation.existsById(idReservation)){
            this.exceptionFormatter.responseEntityNotFound("Reservation was not found...");
        }

        Reservation objReservation = this.gatewayReservation.findById(idReservation);

        return objReservation;
    }

}
