package com.jdacamacho.hexagonal.Domain.Objects;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reservation {
    private long id;
    private User objUser;
    private Field objField;
    private String hour;
    private double price;
    private Date createAt;

    public Reservation(){
        
    }

}
