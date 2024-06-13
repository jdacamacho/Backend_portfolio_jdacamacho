package com.jdacamacho.hexagonal.Domain.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long documentNumber;
    private String documentType;
    private String names;
    private String lastNames;
    private String username;
    private String password;
    private List<Role> roles;

    public User(){
        
    }

}
