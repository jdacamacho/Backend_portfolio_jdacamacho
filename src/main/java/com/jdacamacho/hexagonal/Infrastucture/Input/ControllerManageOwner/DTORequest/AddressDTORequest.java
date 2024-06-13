package com.jdacamacho.hexagonal.Infrastucture.Input.ControllerManageOwner.DTORequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTORequest {
    @NotBlank(message = "Street cannot be blank")
    @Size(min = 3, max = 20, message = "Street must be between 3 and 20 characters")
    private String street;

    @NotBlank(message = "Number cannot be blank")
    @Size(min = 1, max = 10, message = "Number must be between 1 and 10 characters")
    private String number;

    @NotBlank(message = "Floor or apartment cannot be blank")
    @Size(min = 1, max = 10, message = "Floor or apartment must be between 1 and 10 characters")
    private String floorOrApartment;

    @NotBlank(message = "Neighborhood cannot be blank")
    @Size(min = 1, max = 20, message = "Neighborhood must be between 1 and 20 characters")
    private String neighborhood;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 3, max = 20, message = "City must be between 3 and 50 characters")
    private String city;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 3, max = 20, message = "Country must be between 3 and 50 characters")
    private String country;

    public AddressDTORequest(){

    }
}
