package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDTOResponse {
    private long id;
    private String name;

    public RoleDTOResponse(){
        
    }

}
