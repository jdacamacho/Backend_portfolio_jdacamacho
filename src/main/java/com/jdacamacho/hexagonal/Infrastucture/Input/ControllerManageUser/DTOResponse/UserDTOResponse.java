package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTOResponse {
    private long idUser;
    private String nameUser;
    private String lastNameUser;
    private String nickNameUser;

    public UserDTOResponse(){
        
    }
}
