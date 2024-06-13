package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTOResponse;

import java.util.List;

import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.RoleDTOResponse;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerDTOResponse extends UserDTOResponse {
    private long nit;
    private String propertyName;
    private AddressDTOResponse address;

    public OwnerDTOResponse(){
        super();
    }
    
    public OwnerDTOResponse(long id, long documentNumber, String documentType, String names,
                    String lastNames,String username, List<RoleDTOResponse> roles,
                    long nit, String propertyName, AddressDTOResponse address){
        super(id, documentNumber, documentType, names, lastNames, username, roles);
        this.nit = nit;
        this.propertyName = propertyName;
        this.address = address;
    } 
}
