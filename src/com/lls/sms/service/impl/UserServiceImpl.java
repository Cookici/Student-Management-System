package com.lls.sms.service.impl;

import com.lls.sms.dao.UserDao;
import com.lls.sms.dao.impl.UserDaoImpl;
import com.lls.sms.entity.User;
import com.lls.sms.service.UserService;
import com.lls.sms.utils.JdbcUtil;
import com.lls.sms.utils.MD5Util;


/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.service.impl
 * @ClassName: UserServiceImpl
 * @Author: 63283
 * @Description: 实现类
 * @Date: 2023/5/30 22:01
 */

public class UserServiceImpl implements UserService {


    private UserDao userDao = new UserDaoImpl();

    @Override
    public int enroll(String username, String password, String authority) {
        int i = 0;
        User user = null;
        try {
            user = new User(null, username, password, authority);
            i = userDao.insertUser(user);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return i;
    }

    @Override
    public boolean login(String username, String password) {
        User user = null;
        try {
            user = userDao.getUser(username);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        String passwordByUsername = user.getPassword();
        if (passwordByUsername != null) {
            String passwordDecrypt = MD5Util.convertMd5(passwordByUsername);
            return MD5Util.md5(password).equals(passwordDecrypt);
        }
        return false;
    }

    @Override
    public boolean usernameIfExist(String username) {
        User user = null;
        try {
            user = userDao.getUser(username);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return user.getUsername() == null;
    }


    @Override
    public String getAuthority(String username) {
        User user = null;
        try {
            user = userDao.getUser(username);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return user.getAuthority();
    }
}
