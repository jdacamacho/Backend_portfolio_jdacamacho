package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTOResponse;

import java.util.Date;

import com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse.UserDTOResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthDTOResponse {
    private UserDTOResponse objUser;
    private String token;
    private Date authenticatedAt;

    public UserAuthDTOResponse(){

    }
}
