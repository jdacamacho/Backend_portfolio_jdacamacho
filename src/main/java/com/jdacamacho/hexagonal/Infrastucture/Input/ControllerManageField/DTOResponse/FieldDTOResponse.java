package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse;

import java.util.ArrayList;
import java.util.List;
import com.jdacamacho.hexagonal.Domain.Objects.Owner;

import lombok.Data;

@Data
public class FieldDTOResponse {
    private long id;
    private String name;
    private int numberPlayers;
    private boolean state;
    private Owner objOwner;
    private List<ScheduleDTOResponse> schedules;

    public FieldDTOResponse(){
        this.schedules = new ArrayList<>();
    }
}
