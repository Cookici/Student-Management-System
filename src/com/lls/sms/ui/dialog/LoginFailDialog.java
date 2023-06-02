package com.lls.sms.ui.dialog;
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

public class LoginFailDialog extends JOptionPane {

    public LoginFailDialog(String username, String password){
        if (StringUtils.isNullOrEmpty(username) || StringUtils.isNullOrEmpty(password)) {
            JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "登陆失败", JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(null, "用户名或密码错误", "登陆失败", JOptionPane.ERROR_MESSAGE);
        }
    }

}
