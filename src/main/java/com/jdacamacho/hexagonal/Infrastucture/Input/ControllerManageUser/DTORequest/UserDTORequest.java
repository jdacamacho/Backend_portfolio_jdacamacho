package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTORequest {

    @NotNull(message = "Document number cannot be null")
    private Long documentNumber;

    @NotBlank(message = "Document type cannot be blank")
    @Size(min = 3, max = 20, message = "Document type must be between 3 and 20 characters")
    private String documentType;

    @NotBlank(message = "Names cannot be blank")
    @Size(min = 3, max = 20, message = "Names must be between 3 and 80 characters")
    private String names;

    @NotBlank(message = "Last names cannot be blank")
    @Size(min = 3, max = 20, message = "Last names must be between 3 and 80 characters")
    private String lastNames;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Valid
    @NotNull(message = "Roles cannot be null")
    @Size(min = 1, message = "There must be at least one role")
    private List<RoleDTORequest> roles;

    public UserDTORequest(){
        this.roles = new ArrayList<>();
    }
}
