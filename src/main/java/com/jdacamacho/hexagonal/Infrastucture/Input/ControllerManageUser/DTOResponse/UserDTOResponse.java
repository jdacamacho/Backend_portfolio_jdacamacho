package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTOResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTOResponse {
    private long id;
    private long documentNumber;
    private String documentType;
    private String names;
    private String lastNames;
    private String username;
    //private String password;
    private List<RoleDTOResponse> roles;

    public UserDTOResponse(){
        this.roles = new ArrayList<>();
    }
}
