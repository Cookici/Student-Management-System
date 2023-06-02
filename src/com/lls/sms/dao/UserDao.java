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
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 登录
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * 检测是否存在相同用户名
     * @param username
     * @return
     */
    User getUserByName(String username);


}
