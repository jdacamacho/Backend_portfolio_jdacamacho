package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageReservation.DTOResponse;

import java.util.Date;

import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageField.DTOResponse.FieldDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationDTOResponse {
    private long id;
    private boolean pay;
    private UserDTOResponse objUser;
    private FieldDTOResponse objField;
    private int hour;
    private double price;
    private Date createAt;

    public ReservationDTOResponse(){
        
    }
}
