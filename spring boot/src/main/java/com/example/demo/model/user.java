package com.example.demo.model;

/**
 * 用户实体类
 */
public class user {

    private String id;  //ID
    private String userName;  //用户名
    private String password;  //密码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
