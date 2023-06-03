package com.lls.sms.service;


/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.service
 * @ClassName: UserService
 * @Author: 63283
 * @Description: 接口
 * @Date: 2023/5/30 22:01
 */

public interface UserService {


    int enroll(String username,String password,String authority);


    boolean login(String username,String password);


    boolean usernameIfExist(String username);

    String getAuthority(String username);

}
