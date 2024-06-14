package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

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
@Table(name = "Schedules")
@Data
@AllArgsConstructor
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private long id;

    @Column(name = "schedule_booking_time", length = 30, nullable = false)
    private String bookingTime;

    @Column(name = "schedule_hour", nullable = false)
    private int hour;

    @Column(name = "schedule_price" , nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "schedule_field_id")
    private FieldEntity objField;

    public ScheduleEntity(){

    }



}
