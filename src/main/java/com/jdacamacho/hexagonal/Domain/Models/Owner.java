package com.jdacamacho.hexagonal.Domain.Models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Owner extends User {
    private long nit;
    private String propertyName;
    private Address address;

    public Owner(){
        super();
    }

    public Owner(long documentNumber, String documentType, String names,
                    String lastNames,String username, String password, List<Role> roles,
                    long nit, String propertyName, Address address){
        super(documentNumber, documentType, names, lastNames, username, password, roles);
        this.nit = nit;
        this.propertyName = propertyName;
        this.address = address;
    } 
}