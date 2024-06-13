package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest;

import java.util.List;

import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.RoleDTORequest;
import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest.UserDTORequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerUpdateDTORequest extends UserDTORequest {
    @NotNull(message = "NIT cannot be null")
    private Long nit;

    @NotBlank(message = "Property name cannot be blank")
    @Size(min = 3, max = 80, message = "Property Name must be between 3 and 40 characters")
    private String propertyName;

    @NotNull(message = "Address cannot be null")
    @Valid
    private AddressUpdateDTORequest address;

    public OwnerUpdateDTORequest(){
        super();
    }
    
    public OwnerUpdateDTORequest(long documentNumber, String documentType, String names,
                    String lastNames,String username, String password, List<RoleDTORequest> roles,
                    long nit, String propertyName, AddressUpdateDTORequest address){
        super(documentNumber, documentType, names, lastNames, username, password, roles);
        this.nit = nit;
        this.propertyName = propertyName;
        this.address = address;
    } 
}
