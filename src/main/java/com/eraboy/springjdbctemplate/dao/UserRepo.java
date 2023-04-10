package com.eraboy.springjdbctemplate.dao;

import com.eraboy.springjdbctemplate.entity.User;

import java.util.List;

public interface UserRepo {
    User saveUser(User user);

    User updateUser(User user);

    User getById(int id);

    String deleteById(int id);

    List<User> allUsers();
}
