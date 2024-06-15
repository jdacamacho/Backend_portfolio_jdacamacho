package com.jdacamacho.hexagonal.Domain.Objects;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schedule {
    private long id;
    private String bookingTime;
    private String day;
    private int hour;
    private boolean state;
    private double price;
    private Field objField;
    
    public Schedule(){
        
    }

    public void enableSchedule(){
        this.setState(true);
    }

    public void disableSchedule(){
        this.setState(false);
    }

    public boolean hourIsValid(){
        return this.hour >= 0 && this.hour <= 12;
    }

    public boolean isValidDay(){
        String[] validDays = {"monday","tuesday","wednesday","thursday","friday","saturday","sunday"};
        String lowerCaseType = this.getDay().toLowerCase();
        return Arrays.stream(validDays)
                                .anyMatch(validDay -> validDay.equals(lowerCaseType));
    }
}
