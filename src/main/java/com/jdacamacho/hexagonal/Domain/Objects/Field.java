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
    private boolean state;
    private Owner objOwner;
    private List<Schedule> schedules;
    private List<Reservation> reservations;

    public Field(){
        this.schedules = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public void asignOwner(Owner owner){
        this.setObjOwner(owner);
    }

    public void update(Field field){
        this.setName(field.getName());
        this.setNumberPlayers(field.getNumberPlayers());
        this.setState(field.isState());
    }

}
