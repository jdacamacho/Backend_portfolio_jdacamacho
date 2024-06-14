package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTORequest {
   @NotBlank(message = "Booking time cannot be blank")
    private String bookingTime;
    
    @NotBlank(message = "Hour cannot be blank")
    private String hour;
    
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private double price;

    public ScheduleDTORequest(){

    }
}
