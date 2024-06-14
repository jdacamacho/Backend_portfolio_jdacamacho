package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.OwnerEntity;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerEntity, Long>{
    public List<OwnerEntity> findByPropertyNameContainingIgnoreCase(String propertyName);
    public boolean existsByDocumentNumber(long documentNumber);
    public boolean existsByPropertyName(String propertyName);
    public boolean existsByUsername(String username);
    public boolean existsByNit(long nit);
}
