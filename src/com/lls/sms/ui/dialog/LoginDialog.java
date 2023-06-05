package com.lls.sms.ui.dialog;
import com.lls.sms.service.UserService;
import com.lls.sms.ui.StudentUI;
import com.lls.sms.ui.UserUI;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;


/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.dialog
 * @ClassName: LoginFail
 * @Author: 63283
 * @Description: 登陆弹窗
 * @Date: 2023/5/31 15:20
 */

public class LoginDialog extends JOptionPane {

    public LoginDialog(String username, String password, UserService userService, UserUI userUi){

        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "登陆失败", JOptionPane.ERROR_MESSAGE);
        } else {
            if (userService.login(username, password)) {
                JOptionPane.showMessageDialog(null, "登录成功", "登录成功", JOptionPane.PLAIN_MESSAGE);
                String authority = userService.getAuthority(username);
                userUi.dispose();
                new StudentUI(authority);
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误", "登陆失败", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
