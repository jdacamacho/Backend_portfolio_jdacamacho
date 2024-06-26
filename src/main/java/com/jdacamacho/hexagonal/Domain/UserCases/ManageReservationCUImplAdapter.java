package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.List;
import java.util.stream.Collectors;

import com.jdacamacho.hexagonal.Application.Input.ManageReservationCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageFieldGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageReservationGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageScheduleGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Field;
import com.jdacamacho.hexagonal.Domain.Objects.Reservation;
import com.jdacamacho.hexagonal.Domain.Objects.Schedule;
import com.jdacamacho.hexagonal.Domain.Objects.User;

public class ManageReservationCUImplAdapter implements ManageReservationCUIntPort {
    private final ManageReservationGatewayIntPort gatewayReservation;
    private final ManageUserGatewayIntPort gatewayUser;
    private final ManageFieldGatewayIntPort gatewayField;
    private final ManageScheduleGatewayIntPort gatewaySchedule;
    private final ExceptionFormatterIntPort exceptionFormatter;
    
    public ManageReservationCUImplAdapter(ManageReservationGatewayIntPort gatewayReservation,
                                        ManageUserGatewayIntPort gatewayUser,
                                        ManageScheduleGatewayIntPort gatewaySchedule,
                                        ManageFieldGatewayIntPort gatewayField,
                                        ExceptionFormatterIntPort exceptionFormatter){
        this.gatewayReservation = gatewayReservation;
        this.gatewayUser = gatewayUser;
        this.gatewaySchedule = gatewaySchedule;
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

        if(objUser.getReservations().isEmpty()){
            this.exceptionFormatter.responseEntityNotFound("User has no reservations...");
        }

        return objUser.getReservations();
    }

    @Override
    public Reservation makeReservation(long idUser, long idField, long idSchedule) {
        
        if(!this.gatewayUser.existsById(idUser)){
            this.exceptionFormatter.responseEntityNotFound("User was not found...");
        }

        if(!this.gatewayField.existById(idField)){
            this.exceptionFormatter.responseEntityNotFound("Field was not found...");  
        }
        
        if(!this.gatewaySchedule.existsById(idSchedule)){
            this.exceptionFormatter.responseEntityNotFound("Schedule was not found...");
        }

        Reservation reservation = new Reservation();
        User user = this.gatewayUser.findById(idUser);
        Field field = this.gatewayField.findById(idField);
        Schedule schedule = this.gatewaySchedule.findById(idSchedule);


        if(!schedule.isState()){
            this.exceptionFormatter.responseBusinessRuleViolates("This schedule is busy...");
        }

        reservation.setValues(user, field, schedule);
        reservation.setUserReservation();
        reservation.setFieldReservation();

        if(!reservation.scheduleBelongToField(schedule)){
            this.exceptionFormatter.responseBusinessRuleViolates("Schedule does not belong to this field...");
        }

        schedule.disableSchedule();
        reservation.updateStateSchedule(schedule);

        this.gatewaySchedule.save(schedule);
        Reservation objReservation = this.gatewayReservation.save(reservation);

        return objReservation;
    }

    @Override
    public boolean cancelReservation(long idReservation) {
        
        if(!this.gatewayReservation.existsById(idReservation)){
            this.exceptionFormatter.responseEntityNotFound("Reservation was not found...");
        }

        Reservation objReservation = this.gatewayReservation.findById(idReservation);
        
        if(!this.gatewaySchedule.existsById(objReservation.getTicket())){
            this.exceptionFormatter.responseEntityNotFound("Ticket was not found...");
        }

        Schedule objSchedule = this.gatewaySchedule.findById(objReservation.getTicket());

        objSchedule.enableSchedule();
        objReservation.updateStateSchedule(objSchedule);

        this.gatewaySchedule.save(objSchedule);
        this.gatewayReservation.delete(objReservation);

        return true;
    }

    @Override
    public Reservation confirmPayReservation(long idReservation) {
        
        if(!this.gatewayReservation.existsById(idReservation)){
            this.exceptionFormatter.responseEntityNotFound("Reservation was not found...");
        }

        Reservation reservation = this.gatewayReservation.findById(idReservation);

        if(!this.gatewaySchedule.existsById(reservation.getTicket())){
            this.exceptionFormatter.responseEntityNotFound("Ticket was not found...");
        }

        Schedule schedule = this.gatewaySchedule.findById(reservation.getTicket());

        schedule.enableSchedule();
        reservation.updateStateSchedule(schedule);
        reservation.confirmPay();

        this.gatewaySchedule.save(schedule);
        Reservation objReservation = this.gatewayReservation.save(reservation);
        
        return objReservation;
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
