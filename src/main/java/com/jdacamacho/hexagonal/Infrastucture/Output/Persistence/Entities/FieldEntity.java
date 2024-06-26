package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "Fields")
@Data
@AllArgsConstructor
public class FieldEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private long id;

    @Column(name = "field_name", nullable = false, length = 30)
    private String name;

    @Column(name = "field_number_players", nullable = false)
    private int numberPlayers;

    @ManyToOne
    @JoinColumn(name = "field_owner_id")
    private OwnerEntity objOwner;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "objField")
    List<ScheduleEntity> schedules;

    @OneToMany(mappedBy = "objField")
    List<ReservationEntity> reservations;

    public FieldEntity(){
        this.schedules = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }


}
