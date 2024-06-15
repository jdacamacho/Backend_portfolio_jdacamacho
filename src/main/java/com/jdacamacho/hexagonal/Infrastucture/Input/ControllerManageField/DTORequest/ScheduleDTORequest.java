package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleDTORequest {

    @NotBlank(message = "Booking time cannot be blank")
    @Size(min = 2, max = 30, message = "Booking time must be between 2 and 30 characters")
    private String bookingTime;

    @NotBlank(message = "Day cannot be blank")
    @Size(min = 2, max = 30, message = "Day must be between 2 and 30 characters")
    private String day;
    
    @NotNull(message = "Hour cannot be null")
    private int hour;

    @NotNull(message = "State cannot be null")
    private Boolean state;
    
    @PositiveOrZero(message = "Price must be a positive number or zero")
    private double price;

    public ScheduleDTORequest(){

    }
}
