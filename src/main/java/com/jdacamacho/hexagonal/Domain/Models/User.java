package com.jdacamacho.hexagonal.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private long idUser;
    private String nameUser;
    private String lastNameUser;
    private String nickNameUser;

    public User(){
        
    }

    public void update(User user){
        this.nameUser = user.getNameUser();
        this.lastNameUser = user.getLastNameUser();
        this.nickNameUser = user.getNickNameUser();
    }

    public boolean nickNameIsEquals(User newUser){
        return this.nickNameUser.equals(newUser.getNickNameUser());
    }

}
