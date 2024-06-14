package com.jdacamacho.hexagonal.Application.Input;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Field;

public interface ManageFieldCUIntPort {
    public List<Field> listFields();
    public List<Field> listFieldsByOwnerId(long idOwner);
    public List<Field> listFieldsByPropertyName(String propertyName);
    public Field saveField(long idOwner, Field field); 
    public Field updateField(long idField, Field field);
    public boolean deleteField(long idField); 
    public Field findFieldById(long idField);
}
