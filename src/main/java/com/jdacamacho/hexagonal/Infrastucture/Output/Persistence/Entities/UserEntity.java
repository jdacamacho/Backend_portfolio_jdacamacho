package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    @Column(nullable = false, length = 40)
    private String nameUser;
    @Column(nullable = false, length = 40)
    private String lastNameUser;
    @Column(nullable = false, length = 20, unique = true)
    private String nickNameUser;

    public UserEntity(){

    }
}
