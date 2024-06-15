package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long>{
    
}
