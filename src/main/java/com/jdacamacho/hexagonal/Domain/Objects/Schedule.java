package com.jdacamacho.hexagonal.Domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private long id;
    private String bookingTime;
    private int hour;
    private boolean state;
    private double price;
    private Field objField;
    
    public Schedule(){
        
    }

    public boolean hourIsValid(){
        return this.hour >= 0 && this.hour <= 12;
    }
}
