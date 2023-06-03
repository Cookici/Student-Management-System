package com.lls.sms.dao;

import com.lls.sms.entity.User;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.dao
 * @ClassName: adminDao
 * @Author: 63283
 * @Description: 接口
 * @Date: 2023/5/30 16:20
 */

public interface UserDao {

    /**
     * 注册
     */
    int insertUser(User user);

    /**
     * 登录和检测用户是否存在
     */
    User getUser(String username);



}
