package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageLogin.DTORequest;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CredentialDTORequest {
    
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public CredentialDTORequest(){

    }
}
