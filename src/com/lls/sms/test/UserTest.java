package com.lls.sms.test;

import com.lls.sms.service.UserService;
import com.lls.sms.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.test
 * @ClassName: UserTest
 * @Author: 63283
 * @Description: User测试
 * @Date: 2023/5/30 22:10
 */

public class UserTest {

    private UserService userService = new UserServiceImpl();


    @Test
    public void testEnroll() {
        int enroll = userService.enroll("lrh123456", "lrh123456", "admin");
        Assert.assertNotEquals(0, enroll);
    }

    @Test
    public void testLogin() {
        boolean login = userService.login("lrh123456", "lrh123456");
        Assert.assertNotEquals(false, login);

    }


    @Test
    public void testUsernameIfExist() {
        boolean exist = userService.usernameIfExist("lrh123456");
        Assert.assertNotEquals(true, exist);
    }

    @Test
    public void testGetAuthority() {
        String authority = userService.getAuthority("lrh123456");
        Assert.assertEquals("admin", authority);
    }

}
