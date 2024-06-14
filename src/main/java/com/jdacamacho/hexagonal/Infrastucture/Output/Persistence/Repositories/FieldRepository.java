package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.FieldEntity;

@Repository
public interface FieldRepository extends JpaRepository<FieldEntity, Long> {
    
}
