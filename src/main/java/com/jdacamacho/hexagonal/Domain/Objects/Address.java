package com.jdacamacho.hexagonal.Domain.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private long id;
    private String street;
    private String number;
    private String floorOrApartment;
    private String neighborhood;
    private String city;
    private String country;
    private Owner objOwner;

    public Address(){

    }
}
