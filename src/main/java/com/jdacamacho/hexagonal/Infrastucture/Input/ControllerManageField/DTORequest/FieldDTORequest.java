package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTORequest;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldDTORequest {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    
    @PositiveOrZero(message = "Number of players must be a positive number or zero")
    private int numberPlayers;
    
    @NotNull(message = "State cannot be null")
    private Boolean state;
    
    @Valid 
    @Size(min = 1, message = "At least one schedule must be provided")
    private List<ScheduleDTORequest> schedules;

    public FieldDTORequest(){
        this.schedules = new ArrayList<>();
    }
}
