package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
    public List<UserEntity> findAllByNamesContainingIgnoreCase(String names);
    public boolean existsByDocumentNumber(long documentNumber);
    public boolean existsByUsername(String username);
}
