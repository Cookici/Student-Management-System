package com.lls.sms.dao.impl;

import com.lls.sms.dao.BaseDao;
import com.lls.sms.dao.UserDao;
import com.lls.sms.entity.User;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.dao.impl
 * @ClassName: UserDaoImpl
 * @Author: 63283
 * @Description: 实现类
 * @Date: 2023/5/30 21:55
 */

public class UserDaoImpl extends BaseDao<User> implements UserDao{

    @Override
    public int insertUser(User user){
        return super.update("insert into t_user values(null,?,?,?)",user.getUsername(),user.getPassword(),user.getAuthority());
    }

    @Override
    public User getUser(String username){
        return super.queryForOne("select username,password,authority from t_user where username = ?",username);
    }


}
