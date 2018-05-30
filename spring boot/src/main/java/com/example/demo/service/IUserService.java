package com.example.demo.service;

import com.example.demo.model.user;

import java.util.List;

/**
 * 用户service层
 */
public interface IUserService {

    void addNewUser(String id, String userName, String password);

    List<user> findUsersByCondition(String id, String userName);

    user findUserById(String id);

    void update(String id, String userName, String password);

    void delete(String id);
}
