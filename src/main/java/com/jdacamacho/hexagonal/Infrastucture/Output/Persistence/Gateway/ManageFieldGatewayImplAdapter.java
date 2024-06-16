package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageFieldGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Objects.Field;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.FieldEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.FieldRepository;

@Service
public class ManageFieldGatewayImplAdapter implements ManageFieldGatewayIntPort{
    private final FieldRepository serviceBD;
    private final ModelMapper mapper;

    public ManageFieldGatewayImplAdapter(FieldRepository serviceBD,
                                ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
    }

    @Override
    public List<Field> findAll() {
        Iterable<FieldEntity> data = this.serviceBD.findAll();
        List<Field> response = this.mapper.map(data, new TypeToken<List<Field>>(){}.getType());
        return response;
    }

    @Override
    public Field save(Field field) {
        FieldEntity fieldToSave = this.mapper.map(field, FieldEntity.class);
        FieldEntity fieldSaved = this.serviceBD.save(fieldToSave);
        Field response = this.mapper.map(fieldSaved, Field.class);
        return response;
    }

    @Override
    public void delete(Field field) {
        FieldEntity fieldToDelete = this.mapper.map(field, FieldEntity.class);
        this.serviceBD.delete(fieldToDelete);
    }

    @Override
    public Field findById(long id) {
        FieldEntity data = this.serviceBD.findById(id).get();
        return this.mapper.map(data, Field.class);
    }

    @Override
    public boolean existById(long id) {
        return this.serviceBD.existsById(id);
    }

}
