package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    @Bean
    public ModelMapper createMapper(){
        return new ModelMapper();
    }
}
