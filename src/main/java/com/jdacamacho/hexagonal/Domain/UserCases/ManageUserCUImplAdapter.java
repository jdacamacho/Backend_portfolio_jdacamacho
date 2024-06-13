package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.List;

import com.jdacamacho.hexagonal.Application.Input.ManageUserCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageRolegatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.User;

public class ManageUserCUImplAdapter implements ManageUserCUIntPort{
    private final ManageUserGatewayIntPort gatewayUser;
    private final ManageRolegatewayIntPort gatewayRole;
    private final ExceptionFormatterIntPort exceptionFormatter;

    public ManageUserCUImplAdapter(ManageUserGatewayIntPort gatewayUser,
                                ManageRolegatewayIntPort gatewayRole,
                                ExceptionFormatterIntPort exceptionFormatter){
        this.gatewayUser = gatewayUser;
        this.gatewayRole = gatewayRole;
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public List<User> listUsers() {
        List<User> users = this.gatewayUser.findAll();
        if(users.isEmpty()){
            this.exceptionFormatter.responseNoData("There are no users in BD...");
        }
        return users;
    }

    @Override
    public User saveUser(User user) {
        User objUser = null;

        if(this.gatewayUser.existsByDocumentNumber(user.getDocumentNumber()) ){
            this.exceptionFormatter.responseEntityExists("User with that document number already exists in the BD...");
        }
        if(this.gatewayUser.existsByUsername(user.getUsername())){
            this.exceptionFormatter.responseEntityExists("User with that username number already exists in the BD...");
        }
        if(!user.isValidDocumentType()){
            this.exceptionFormatter.responseBusinessRuleViolates("User has a document type not valid");
        }
        if(!user.rolesAreValid(this.gatewayRole.findAll())){
            this.exceptionFormatter.responseBusinessRuleViolates("User roles are not valid");
        }
        if(user.hasDuplicatedRoles()){
            this.exceptionFormatter.responseBusinessRuleViolates("User has duplicate roles");
        }

        objUser = this.gatewayUser.save(user);
        return objUser;
    }

    @Override
    public User updateUser(long id, User user) {
        User newUser = null;
        User oldUser = null;

        if(!this.gatewayUser.existsById(id)){
            this.exceptionFormatter.responseEntityNotFound("User with that id was not found in the BD...");
        }else{
            oldUser = this.gatewayUser.findById(id);

            if(this.gatewayUser.existsByDocumentNumber(user.getDocumentNumber()) ){
                if(!oldUser.documentNumberIsEquals(user)){
                    this.exceptionFormatter.responseEntityExists("User with that document number already exists in the BD...");
                } 
            }
            if(this.gatewayUser.existsByUsername(user.getUsername())){
                if(!oldUser.usernameIsEquals(user)){
                    this.exceptionFormatter.responseEntityExists("User with that username number already exists in the BD...");
                }
            } 
            if(!user.isValidDocumentType()){
                this.exceptionFormatter.responseBusinessRuleViolates("User has a document type not valid");
            }
            if(!user.rolesAreValid(this.gatewayRole.findAll())){
                this.exceptionFormatter.responseBusinessRuleViolates("User roles are not valid");
            }
            if(user.hasDuplicatedRoles()){
                this.exceptionFormatter.responseBusinessRuleViolates("User has duplicate roles");
            }
        }

        oldUser.update(user);
        newUser = this.gatewayUser.save(oldUser);

        return newUser;
    }

    @Override
    public User findById(long idUser) {
        User objUser = null;
        if(!this.gatewayUser.existsById(idUser)){
            this.exceptionFormatter.responseEntityNotFound("User with that id was not found...");
        }
        objUser = this.gatewayUser.findById(idUser);
        return objUser;
    }
    
}
