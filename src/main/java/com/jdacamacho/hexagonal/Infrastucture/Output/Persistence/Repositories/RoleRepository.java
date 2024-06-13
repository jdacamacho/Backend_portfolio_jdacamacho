package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long>{
    @Query("SELECT r FROM RoleEntity r WHERE r.name = 'Administrator'")
    List<RoleEntity> findAllExceptAdministrator();
}
