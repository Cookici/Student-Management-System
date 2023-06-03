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


    /**
     * 注册
     */
    int enroll(String username, String password, String authority);


    /**
     * 登录
     */
    boolean login(String username, String password);

    /**
     * 判断用户是否存在
     */
    boolean usernameIfExist(String username);

    /**
     * 获取权限
     */
    String getAuthority(String username);

}
