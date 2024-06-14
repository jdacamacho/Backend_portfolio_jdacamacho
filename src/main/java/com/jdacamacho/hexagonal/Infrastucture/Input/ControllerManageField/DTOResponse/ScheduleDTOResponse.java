package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTOResponse {
    private long id;
    private String bookingTime;
    private int hour;
    private double price;

    public ScheduleDTOResponse(){
        
    }
}
