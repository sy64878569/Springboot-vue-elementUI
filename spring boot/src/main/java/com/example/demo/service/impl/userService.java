package com.example.demo.service.impl;

import com.example.demo.model.user;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户Service层实现
 */
@Service()
public class userService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addNewUser(String id, String userName, String password) {
        userMapper.addNewUser(id,userName,password);
    }

    @Override
    public List<user> findUsersByCondition(String id, String userName) {
        List<user> userList = userMapper.findUsersByCondition(id,userName);
        return userList;
    }

    @Override
    public user findUserById(String id) {
        user user = userMapper.findUserById(id);
        return user;
    }

    @Override
    public void update(String id, String userName, String password) {
        userMapper.update(id, userName, password);
    }

    @Override
    public void delete(String id) {
        userMapper.delete(id);
    }


}
