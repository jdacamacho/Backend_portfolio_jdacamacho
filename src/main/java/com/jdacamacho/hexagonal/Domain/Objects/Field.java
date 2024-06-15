package com.jdacamacho.hexagonal.Domain.Objects;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Field {
    private long id;
    private String name;
    private int numberPlayers;
    private Owner objOwner;
    private List<Schedule> schedules;
    private List<Reservation> reservations;

    public Field(){
        this.schedules = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public boolean schedulesHourAreValid(){
        for(Schedule schedule : this.getSchedules()){
            if(!schedule.hourIsValid()){
                return false;
            }
        }
        return true;
    }

    public boolean schedulesDayAreValid(){
        for(Schedule schedule: this.getSchedules()){
            if(!schedule.isValidDay()){
                return false;
            }
        }
        return true;
    }

    public void asignOwner(Owner owner){
        this.setObjOwner(owner);
    }

    public void asigFieldToOwner(){
        this.getObjOwner().getFields().forEach(field -> field.setObjOwner(this.getObjOwner()));
    }

    public void asignScheduleToField(){
        this.getSchedules().forEach(schedule -> schedule.setObjField(this));
    }

    public void update(Field field){
        this.setName(field.getName());
        this.setNumberPlayers(field.getNumberPlayers());
        this.setSchedules(field.getSchedules());
        this.asignScheduleToField();
    }

}
