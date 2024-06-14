package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Objects.Field;

public interface ManageFieldGatewayIntPort {
    public List<Field> findAll();
    public Field save(Field field);
    public void delete(Field field);
    public Field findById(long id);
    public boolean existById(long id);
}
