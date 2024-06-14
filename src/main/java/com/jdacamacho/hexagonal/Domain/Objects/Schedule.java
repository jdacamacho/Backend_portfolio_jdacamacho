package com.jdacamacho.hexagonal.Domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private long id;
    private String bookingTime;
    private String hour;
    private double price;
    private Field objField;
    
    public Schedule(){
        
    }
}
