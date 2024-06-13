package com.jdacamacho.hexagonal.Domain.Objects;

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

    public Owner(long id,long documentNumber, String documentType, String names,
                    String lastNames,String username, String password, List<Role> roles,
                    long nit, String propertyName, Address address){
        super(id,documentNumber, documentType, names, lastNames, username, password, roles);
        this.nit = nit;
        this.propertyName = propertyName;
        this.address = address;
    } 

    public void assignAddress(){
        Address userAddress = this.getAddress();
        userAddress.setObjOwner(this);
    }

    public void update(Owner owner){
        this.setDocumentNumber(owner.getDocumentNumber());
        this.setDocumentType(owner.getDocumentType());
        this.setNames(owner.getNames());
        this.setLastNames(owner.getLastNames());
        this.setUsername(owner.getUsername());
        this.setPassword(owner.getPassword());
        this.setNit(owner.getNit());
        this.setPropertyName(owner.getPropertyName());
        this.getAddress().setStreet(owner.getAddress().getStreet());
        this.getAddress().setNumber(owner.getAddress().getNumber());
        this.getAddress().setNeighborhood(owner.getAddress().getNeighborhood());
        this.getAddress().setFloorOrApartment(owner.getAddress().getFloorOrApartment());
        this.getAddress().setCity(owner.getAddress().getCity());
        this.getAddress().setCountry(owner.getAddress().getCountry());
    }
}