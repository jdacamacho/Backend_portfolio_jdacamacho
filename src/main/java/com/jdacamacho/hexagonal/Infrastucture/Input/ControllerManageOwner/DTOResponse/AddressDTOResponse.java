package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTOResponse;

import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest.OwnerDTORequest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTOResponse {
    private long id;
    private String street;
    private String number;
    private String floorOrApartment;
    private String neighborhood;
    private String city;
    private String country;
    private OwnerDTORequest objOwner;

    public AddressDTOResponse(){
        
    }
}
