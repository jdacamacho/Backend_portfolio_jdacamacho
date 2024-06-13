package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageUser.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTORequest {
    @NotNull(message = "name can't be null")
    @NotBlank(message = "name can't be empty")
    @Size(min = 2, max = 45, message = "name must be between 2 and 45 characters")
    private String nameUser;

    @NotNull(message = "lastname can't be null")
    @NotBlank(message = "lastname can't be empty")
    @Size(min = 2, max = 45, message = "lastname must be between 2 and 45 characters")
    private String lastNameUser;

    @NotNull(message = "nickname can't be null")
    @NotBlank(message = "nickName can't be empty")
    @Size(min = 2, max = 45, message = "nickname must be between 2 and 45 characters")
    private String nickNameUser;

    public UserDTORequest(){

    }
}
