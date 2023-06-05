package com.lls.sms.ui.dialog;

import com.lls.sms.service.UserService;
import com.lls.sms.ui.UserUI;
import com.lls.sms.utils.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.dialog
 * @ClassName: EnrollDialog
 * @Author: 63283
 * @Description: 注册弹窗
 * @Date: 2023/5/31 15:33
 */

public class EnrollDialog extends JDialog {

    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    private JLabel usernameLabel = new JLabel("用户名：");
    private JTextField usernameText = new JTextField();
    private JLabel usernameMsgLabel = new JLabel("由字母数字下划线组成且开头必须是字母,不超过8位");
    private JLabel passwordLabel = new JLabel("密码：");
    private JTextField passwordText = new JTextField();
    private JLabel passwordMsgLabel = new JLabel("字母和数字构成,不能超过11位");
    private JLabel vipLabel = new JLabel("激活码：");
    private JTextField vipText = new JTextField();
    private JLabel vipMsgLabel = new JLabel("普通用户注册无需填写");
    private JButton userEnrollBtn = new JButton("用户注册");
    private JButton adminEnrollBtn = new JButton("管理员注册");


    private static final Logger log = Logger.getLogger(String.valueOf(EnrollDialog.class));

    public EnrollDialog(UserUI userUI, UserService userService) {

        super(userUI, "注册", true);

        //字母数字下划线字母开头,不超过8位
        Pattern usernamePattern = Pattern.compile("[a-zA-Z]{1}[a-zA-Z0-9_]{1,8}");
        //字母和数字构成,不能超过11位
        Pattern passwordPattern = Pattern.compile("[a-zA-Z0-9]{1,11}");

        Container container = getContentPane();

        usernameMsgLabel.setFont(new Font("仿宋", Font.ITALIC, 14));
        passwordMsgLabel.setFont(new Font("仿宋", Font.ITALIC, 14));
        vipMsgLabel.setFont(new Font("仿宋", Font.ITALIC, 14));


        usernameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(usernameLabel);

        usernameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(usernameText);

        usernameMsgLabel.setPreferredSize(new Dimension(350, 30));
        jPanel.add(usernameMsgLabel);

        passwordLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(passwordLabel);

        passwordText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(passwordText);

        passwordMsgLabel.setPreferredSize(new Dimension(300, 30));
        jPanel.add(passwordMsgLabel);

        vipLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(vipLabel);

        vipText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(vipText);

        vipMsgLabel.setPreferredSize(new Dimension(300, 30));
        jPanel.add(vipMsgLabel);

        jPanel.add(userEnrollBtn);
        jPanel.add(adminEnrollBtn);

        container.add(jPanel);


        userEnrollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernamePattern.matcher(usernameText.getText()).matches()) {
                    JOptionPane.showMessageDialog(null, "用户名不符合规则", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else if (!passwordPattern.matcher(passwordText.getText()).matches()) {
                    JOptionPane.showMessageDialog(null, "密码不符合规则", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (userService.usernameIfExist(usernameText.getText())) {
                        String password = passwordText.getText();
                        String passwordEncrypt = MD5Util.convertMd5(MD5Util.md5(password));
                        userService.enroll(usernameText.getText(), passwordEncrypt, "user");
                        JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.PLAIN_MESSAGE);
                        log.info(usernameText.getText() + "," + passwordText.getText() + "注册成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名已存在", "注册失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        adminEnrollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernamePattern.matcher(usernameText.getText()).matches()) {
                    JOptionPane.showMessageDialog(null, "用户名不符合规则", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else if (!passwordPattern.matcher(passwordText.getText()).matches()) {
                    JOptionPane.showMessageDialog(null, "密码不符合规则", "注册失败", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (userService.usernameIfExist(usernameText.getText())) {
                        if ("lrh-ldw-syy".equals(vipText.getText())) {
                            String password = passwordText.getText();
                            String passwordEncrypt = MD5Util.convertMd5(MD5Util.md5(password));
                            userService.enroll(usernameText.getText(), passwordEncrypt, "admin");
                            log.info(usernameText.getText() + "," + passwordText.getText() + "注册成功");
                            JOptionPane.showMessageDialog(null, "注册成功", "注册成功", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "激活码错误", "注册失败", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "用户名已存在", "注册失败", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }
}

