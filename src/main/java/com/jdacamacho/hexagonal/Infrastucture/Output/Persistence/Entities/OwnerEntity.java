package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Owners")
@Getter
@Setter
public class OwnerEntity extends UserEntity{
    @Column(name = "owner_nit" , nullable = false, unique = true)
    private long nit;

    @Column(name = "owner_property_name", nullable = false, unique = true, length = 80)
    private String propertyName;

    @OneToOne(fetch = FetchType.EAGER , cascade = { CascadeType.ALL } ,mappedBy = "objOwner")
    private AddressEntity address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "objOwner")
    private List<FieldEntity> fields;

    public OwnerEntity(){
        super();
        this.fields = new ArrayList<>();
    }

    public OwnerEntity(long id,long documentNumber, String documentType, String names,
                    String lastNames,String username, String password, List<RoleEntity> roles,
                    long nit, String propertyName, AddressEntity address){
        super(id,documentNumber, documentType, names, lastNames, username, password, roles);
        this.nit = nit;
        this.propertyName = propertyName;
        this.address = address;
        this.fields = new ArrayList<>();
    }
    
}
