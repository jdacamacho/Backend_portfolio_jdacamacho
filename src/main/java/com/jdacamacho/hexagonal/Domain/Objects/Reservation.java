package com.jdacamacho.hexagonal.Domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
    private long id;
    private Field objField;
    private String hour;
    private double price;

    public Reservation(){
        
    }

}
