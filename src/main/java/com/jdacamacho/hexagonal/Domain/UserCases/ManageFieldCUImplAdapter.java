package com.jdacamacho.hexagonal.Domain.UserCases;

import java.util.List;
import java.util.stream.Collectors;

import com.jdacamacho.hexagonal.Application.Input.ManageFieldCUIntPort;
import com.jdacamacho.hexagonal.Application.Output.ExceptionFormatterIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageFieldGatewayIntPort;
import com.jdacamacho.hexagonal.Application.Output.ManageOwnerGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Field;
import com.jdacamacho.hexagonal.Domain.Objects.Owner;

public class ManageFieldCUImplAdapter implements ManageFieldCUIntPort{
    private final ManageFieldGatewayIntPort gatewayField;
    private final ManageOwnerGatewayIntPort gatewayOwner;
    private final ExceptionFormatterIntPort exceptionFormatter;

    public ManageFieldCUImplAdapter(ManageFieldGatewayIntPort gatewayField,
                                    ManageOwnerGatewayIntPort gatewayOwner,
                                    ExceptionFormatterIntPort exceptionFormatter){
        this.gatewayField = gatewayField;
        this.gatewayOwner = gatewayOwner;
        this.exceptionFormatter = exceptionFormatter;
    }

    @Override
    public List<Field> listFields() {
        List<Field> fields = this.gatewayField.findAll();
        if(fields.isEmpty()){
            this.exceptionFormatter.responseNoData("There are no fields in BD...");
        }
        return fields;
    }

    @Override
    public List<Field> listFieldsByOwnerId(long idOwner) {
        Owner objOwner = null;
        if(!this.gatewayOwner.existsById(idOwner)){
            this.exceptionFormatter.responseEntityNotFound("Owner was not found...");
        }

        objOwner = this.gatewayOwner.findById(idOwner);
        if(objOwner.getFields().isEmpty()){
            this.exceptionFormatter.responseNoData("Owner does not have fields...");
        }
        return objOwner.getFields();

    }

    @Override
    public List<Field> listFieldsByPropertyName(String propertyName) {
        List<Owner> owners = this.gatewayOwner.findOwnerByPropertyName(propertyName);
        if(owners.isEmpty()){
            this.exceptionFormatter.responseEntityNotFound("Owner was not found...");
        }

        return owners.stream()
                     .flatMap(owner -> owner.getFields().stream())
                     .collect(Collectors.toList());

    }

    @Override
    public Field saveField(long idOwner, Field field) {
        Owner objOwner = null;
        Field objField = null;
        if(!this.gatewayOwner.existsById(idOwner)){
            this.exceptionFormatter.responseEntityNotFound("Owner was not found...");
        }

        objOwner = this.gatewayOwner.findById(idOwner);
        field.asignOwner(objOwner);
        objField = this.gatewayField.save(field);
        return objField;
    }

    @Override
    public Field updateField(long idField, Field field) {
        Field newField = null;
        if(!this.gatewayField.existById(idField)){
            this.exceptionFormatter.responseEntityNotFound("Field was not found...");
        }
        
        Field oldField = this.gatewayField.findById(idField);
        oldField.update(field);
        newField = this.gatewayField.save(oldField);
        return newField;
    }

    @Override
    public boolean deleteField(long idField) {
        Field objField = null;
        if(!this.gatewayField.existById(idField)){
            this.exceptionFormatter.responseEntityNotFound("Field was not found");
        }
        objField = this.gatewayField.findById(idField);
        this.gatewayField.delete(objField);
        return true;
    }

    @Override
    public Field findFieldById(long idField) {
        if(!this.gatewayField.existById(idField)){
            this.exceptionFormatter.responseEntityNotFound("Field was not found...");
        }
        return this.gatewayField.findById(idField);
    }


}
