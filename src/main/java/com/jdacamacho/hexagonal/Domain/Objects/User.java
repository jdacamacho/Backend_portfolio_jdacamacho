package com.jdacamacho.hexagonal.Domain.Objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long id;
    private long documentNumber;
    private String documentType;
    private String names;
    private String lastNames;
    private String username;
    private String password;
    private List<Role> roles;

    public User(){

    }

    public boolean hasDuplicatedRoles(){
        Set<Role> validRoleSet = new HashSet<>(this.getRoles());
        return validRoleSet.size() < this.roles.size();
    }

    public boolean rolesAreValid(List<Role> validRoles){
        Set<Role> validRoleSet = new HashSet<>(validRoles);
        return this.getRoles().stream()
            .allMatch(objRole -> validRoleSet.contains(objRole));
    }

    public boolean documentNumberIsEquals(User user){
        return this.getDocumentNumber() == user.getDocumentNumber();
    }

    public boolean usernameIsEquals(User user){
        return this.getUsername().equals(user.getUsername());
    }

    public boolean isValidDocumentType(){
        String[] validTypes = {"cedula ciudadana","cedula extranjera","pasaporte"};
        String lowerCaseType = documentType.toLowerCase();
        return Arrays.stream(validTypes)
                     .anyMatch(validType -> validType.equals(lowerCaseType)); 
    }

    public void update(User user){
        this.setDocumentNumber(user.getDocumentNumber());
        this.setDocumentType(user.getDocumentType());
        this.setNames(user.getNames());
        this.setLastNames(user.getLastNames());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
    }

}
