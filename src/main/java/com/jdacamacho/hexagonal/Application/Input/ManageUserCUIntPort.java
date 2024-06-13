package com.jdacamacho.hexagonal.Application.Input;

import java.util.List;

import com.jdacamacho.hexagonal.Domain.Models.User;

public interface ManageUserCUIntPort {
    public  List<User> listUsers();
    public User saveUser(User user);
    public User updateUser(long id, User user);
    public User findById(long id);
}
