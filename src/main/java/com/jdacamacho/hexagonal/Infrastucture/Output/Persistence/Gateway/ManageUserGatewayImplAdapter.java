package com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Gateway;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.jdacamacho.hexagonal.Application.Output.ManageUserGatewayIntPort;
import com.jdacamacho.hexagonal.Domain.Models.User;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Entities.UserEntity;
import com.jdacamacho.hexagonal.Infrastucture.Output.Persistence.Repositories.UserRepository;

@Service
public class ManageUserGatewayImplAdapter implements ManageUserGatewayIntPort{
    private final UserRepository serviceBD;
    private final ModelMapper mapper;

    public ManageUserGatewayImplAdapter(UserRepository serviceBD,
                                ModelMapper mapper){
        this.serviceBD = serviceBD;
        this.mapper = mapper;
    }


    @Override
    public List<User> findAll() {
        Iterable<UserEntity> data = this.serviceBD.findAll();
        List<User> response = this.mapper.map(data, new TypeToken<List<User>>(){}.getType());
        return response;
    }

    @Override
    public User save(User user) {
        UserEntity userToSave = this.mapper.map(user, UserEntity.class);
        UserEntity userSaved = this.serviceBD.save(userToSave);
        User response = this.mapper.map(userSaved, User.class);
        return response;
    }

    @Override
    public User findById(long userId) {
        UserEntity data = this.serviceBD.findById(userId).get();
        User response = this.mapper.map(data, User.class);
        return response;
    }

    @Override
    public boolean existsById(long idAction) {
        return this.serviceBD.existsById(idAction);
    }


    @Override
    public boolean existsByNickName(String nickName) {
        return this.serviceBD.existsByNickNameUser(nickName);
    }
    
}
