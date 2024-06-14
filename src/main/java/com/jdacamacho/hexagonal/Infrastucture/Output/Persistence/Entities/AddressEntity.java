package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Addresses")
@Data
@AllArgsConstructor
public class AddressEntity {
    @Id
    @Column(name = "address_id")
    private long id;

    @Column(name = "address_street" , nullable = false , length = 20)
    private String street;

    @Column(name = "address_number" , nullable = false , length = 10)
    private String number;

    @Column(name = "address_floor/apartment" , nullable = false , length = 10)
    private String floorOrApartment;

    @Column(name = "address_neighborhood" , nullable = false , length = 20)
    private String neighborhood;

    @Column(name = "address_city" , nullable = false , length = 50)
    private String city;
    
    @Column(name = "address_country" , nullable = false , length = 50)
    private String country;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_document_number")
    private OwnerEntity objOwner;

    public AddressEntity(){
        
    }

}
