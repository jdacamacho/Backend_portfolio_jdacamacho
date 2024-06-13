package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.List;

import com.jdacamacho.hexagonal.Application.Input.ManageUserCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Models.User;

public class ManageUserCUImplAdapter implements ManageUserCUIntPort {
    private final ManageUserGatewayIntPort gateway;
    private final ExceptionFormatterIntPort exceptionFormatter;

    public ManageUserCUImplAdapter( ManageUserGatewayIntPort gateway,
                                    ExceptionFormatterIntPort exceptionFormatter){
        this.gateway = gateway;
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public List<User> listUsers() {
        List<User> users = this.gateway.findAll();
        if(users.size() == 0){
            this.exceptionFormatter.responseNoData("There are no users in BD");
        }
        return users;
    }

    @Override
    public User saveUser(User user) {
        User response = null;
        if(this.gateway.existsByNickName(user.getNickNameUser())){
            this.exceptionFormatter.responseEntityExists("There is a user with this nickName");
        }
        response = this.gateway.save(user);
        return response;

    }

    @Override
    public User updateUser(long idUser, User user) {
        User response = null;
        if(!this.gateway.existsById(idUser)){
            this.exceptionFormatter.responseEntityNotFound("User not found with that id");
        }else{
            User oldUser = this.findById(idUser);
            if(this.gateway.existsByNickName(user.getNickNameUser())){
                if(!oldUser.nickNameIsEquals(user)){
                    this.exceptionFormatter.responseEntityExists("User exists with that nickName");
                }
            }
            oldUser.update(user);
            response = this.gateway.save(oldUser);
        }
        
        return response;
    }

    @Override
    public User findById(long idUser) {
        if(!this.gateway.existsById(idUser)){
            this.exceptionFormatter.responseEntityNotFound("User with that id was not found");
        }
        return this.gateway.findById(idUser);
    }




}
