package com.lls.sms.entity;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.entity
 * @ClassName: User
 * @Author: 63283
 * @Description: 学生实体类
 * @Date: 2023/5/30 21:17
 */

public class User {

    private Integer id;

    private String username;
    private String password;
    private String authority;

    public User() {
    }

    public User(Integer id, String username, String password, String authority) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
