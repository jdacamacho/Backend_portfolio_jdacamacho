package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Reservations")
@Data
@AllArgsConstructor
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "reservation_user_id")
    private UserEntity objUser;

    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "reservation_field_id")
    private FieldEntity objField;

    @Column(name = "reservation_hour" , nullable = false, length = 10)
    private String hour;

    @Column(name = "reservation_price" , nullable = false)
    private double price;

    @Column(name = "reservation_date", nullable = false)
    private Date createAt;

    public ReservationEntity(){

    }

}
