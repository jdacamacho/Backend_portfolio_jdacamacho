package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.jdacamacho.hexagonal.Application.Input.ManageOwnerCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageOwnerGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageRolegatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Owner;

public class ManageOwnerCUImplAdapter implements ManageOwnerCUIntPort {
    private final ManageOwnerGatewayIntPort gatewayOwner;
    private final ManageUserGatewayIntPort gatewayUser;
    private final ManageRolegatewayIntPort gatewayRole;
    private final PasswordEncoder passwordEncoder;
    private final ExceptionFormatterIntPort exceptionFormatter;

    public ManageOwnerCUImplAdapter(ManageOwnerGatewayIntPort gatewayOwner,
                                ManageUserGatewayIntPort gatewayUser,
                                ManageRolegatewayIntPort gatewayRole,
                                PasswordEncoder passwordEncoder,
                                ExceptionFormatterIntPort exceptionFormatter){
        this.gatewayOwner = gatewayOwner;
        this.gatewayUser = gatewayUser;
        this.gatewayRole = gatewayRole;
        this.passwordEncoder = passwordEncoder;
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public List<Owner> listOwners() {
        List<Owner> owners = this.gatewayOwner.findAll();
        
        if(owners.isEmpty()){
            this.exceptionFormatter.responseNoData("There are no owners in the BD...");
        }

        return owners;
    }

    @Override
    public Owner saverOwner(Owner owner) {
        
        if(this.gatewayUser.existsByDocumentNumber(owner.getDocumentNumber()) ){
            this.exceptionFormatter.responseEntityExists("Owner with that document number already exists in the BD...");
        }
        if(this.gatewayUser.existsByUsername(owner.getUsername())){
            this.exceptionFormatter.responseEntityExists("Owner with that username number already exists in the BD...");
        }
        if(!owner.isValidDocumentType()){
            this.exceptionFormatter.responseBusinessRuleViolates("Owner has a document type not valid");
        }
        if(!owner.rolesAreValid(this.gatewayRole.findAll())){
            this.exceptionFormatter.responseBusinessRuleViolates("Owner roles are not valid");
        }
        if(owner.hasDuplicatedRoles()){
            this.exceptionFormatter.responseBusinessRuleViolates("Owner has duplicate roles");
        }

        owner.assignAddress();
        owner.setPasswordEncode(this.passwordEncoder.encode(owner.getPassword()));
        Owner objOwner = this.gatewayOwner.save(owner);
        
        return objOwner;
    }

    @Override
    public Owner updateOwner(long id, Owner owner) {
        
        if(!this.gatewayOwner.existsById(id)){
            this.exceptionFormatter.responseEntityNotFound("Owner with that id was not found in the BD...");
        }

        Owner oldOwner = this.gatewayOwner.findById(id);

        if(this.gatewayUser.existsByDocumentNumber(owner.getDocumentNumber()) ){
            if(!oldOwner.documentNumberIsEquals(owner)){
                this.exceptionFormatter.responseEntityExists("Owner with that document number already exists in the BD...");
            } 
        }
        if(this.gatewayUser.existsByUsername(owner.getUsername())){
            if(!oldOwner.usernameIsEquals(owner)){
                this.exceptionFormatter.responseEntityExists("Owner with that username number already exists in the BD...");
            }
        } 
        if(!owner.isValidDocumentType()){
            this.exceptionFormatter.responseBusinessRuleViolates("Owner has a document type not valid");
        }
        if(!owner.rolesAreValid(this.gatewayRole.findAll())){
            this.exceptionFormatter.responseBusinessRuleViolates("Owner roles are not valid");
        }
        if(owner.hasDuplicatedRoles()){
            this.exceptionFormatter.responseBusinessRuleViolates("Owner has duplicate roles");
        }
        
        oldOwner.update(owner);

        Owner newOwner = this.gatewayOwner.save(oldOwner);

        return newOwner; 
    }

    @Override
    public Owner findOwnerById(long id) {
        
        if(!this.gatewayOwner.existsById(id)){
            this.exceptionFormatter.responseEntityNotFound("Owner with that ID was not found");
        }

        Owner objOwner = this.gatewayOwner.findById(id);
        
        return objOwner;
    }

    
}
