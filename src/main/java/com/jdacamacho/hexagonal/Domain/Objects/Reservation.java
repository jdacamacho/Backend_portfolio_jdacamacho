package com.jdacamacho.hexagonal.Domain.Objects;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
    private long id;
    private long ticket;
    private boolean pay;
    private User objUser;
    private Field objField;
    private int hour;
    private double price;
    private Date createAt;

    public Reservation(){
   
    }

    public boolean scheduleBelongToField(Schedule schedule){
        for(Schedule objSchedule : this.getObjField().getSchedules()){
            if(objSchedule.getId() == schedule.getId()){
                return true;
            }
        }
        return false;
    }

    public void setValues(User user, Field field, Schedule schedule){
        this.setTicket(schedule.getId());
        this.setPay(false);
        this.setObjUser(user);
        this.setObjField(field);
        this.setHour(schedule.getHour());
        this.setPrice(schedule.getPrice());
        this.setCreateAt(new Date());
    }

    public void setUserReservation(){
        this.getObjUser().getReservations().add(this);
    }

    public void setFieldReservation(){
        this.getObjField().getReservations().add(this);
    }

    public void confirmPay(){
        this.setPay(true);
    }

    public void updateStateSchedule(Schedule schedule){
        for(Schedule objSchedule : this.getObjField().getSchedules()){
            if(objSchedule.getId() == schedule.getId()){
                objSchedule.setState(schedule.isState());
            }
        }
    }

}
