package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
    public boolean existsByNickNameUser(String nickNameUser);
}
