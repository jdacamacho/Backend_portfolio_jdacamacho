package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jdacamacho.hexagonal.Domain.Objects.Address;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.AddressEntity;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper createMapper(){
        ModelMapper mapper = new ModelMapper();
        TypeMap<AddressEntity,Address> mapAddress = mapper.emptyTypeMap(AddressEntity.class, Address.class);
        mapAddress.addMappings(m -> m.skip(Address::setObjOwner)).implicitMappings();
        return mapper;
    }
}
