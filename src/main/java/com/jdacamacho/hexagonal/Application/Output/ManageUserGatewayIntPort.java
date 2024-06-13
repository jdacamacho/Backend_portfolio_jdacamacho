package com.jdacamacho.hexagonal.Application.Output;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Models.User;

public interface ManageUserGatewayIntPort {
    public List<User> findAll();
    public User save(User user);
    public User findById(long userId);
    public boolean existsById(long idAction);
    public boolean existsByNickName(String nickName);
}
