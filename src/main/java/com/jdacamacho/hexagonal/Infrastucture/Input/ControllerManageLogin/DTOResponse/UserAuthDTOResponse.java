package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTOResponse;

import java.util.Date;

import com.jdacamacho.hexagonal.Domain.Objects.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthDTOResponse {
    private User objUser;
    private String token;
    private Date authenticatedAt;

    public UserAuthDTOResponse(){
        
    }
}
