package com.lls.sms.ui;


import com.lls.sms.service.UserService;
import com.lls.sms.service.impl.UserServiceImpl;
import com.lls.sms.ui.dialog.EnrollDialog;
import com.lls.sms.ui.dialog.LoginFailDialog;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui
 * @ClassName: UserUI
 * @Author: 63283
 * @Description: 用户界面
 * @Date: 2023/5/30 23:00
 */

public class UserUI extends JFrame {


    private UserService userService = new UserServiceImpl();
    private JLabel titleLabel = new JLabel("用户登录", JLabel.CENTER);
    private SpringLayout springLayout = new SpringLayout();
    private JPanel centerPanel = new JPanel(springLayout);
    private JLabel usernameLabel = new JLabel("用户名:");
    private JTextField usernameText = new JTextField();
    private JLabel passwordLabel = new JLabel("密码:");
    private JPasswordField passwordText = new JPasswordField();
    private JButton loginBtn = new JButton("登录");
    private JButton enrollBtn = new JButton("注册");

    private JCheckBox checkBox = new JCheckBox("显示密码");

    public UserUI() {

        super("学生管理系统");
        Container container = getContentPane();
        titleLabel.setFont(new Font("华文行楷", Font.PLAIN, 40));
        titleLabel.setPreferredSize(new Dimension(0, 80));

        Font other = new Font("楷体", Font.PLAIN, 20);

        usernameText.setPreferredSize(new Dimension(250, 30));

        passwordText.setPreferredSize(new Dimension(250, 30));

        loginBtn.setFont(other);
        enrollBtn.setFont(other);

        centerPanel.add(usernameLabel);
        centerPanel.add(usernameText);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordText);

        centerPanel.add(loginBtn);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = passwordText.getText();
                if (userService.login(username, password)) {
                    JOptionPane.showMessageDialog(null, "登录成功", "登录成功", JOptionPane.PLAIN_MESSAGE);
                    String authority = userService.getAuthority(username);
                    UserUI.this.dispose();
                    new StudentUI(authority);
                } else {
                    new LoginFailDialog(username, password);
                }
            }
        });



        centerPanel.add(enrollBtn);
        enrollBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EnrollDialog(UserUI.this,userService);
            }
        });

        centerPanel.add(checkBox);
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){//被选中
                    passwordText.setEchoChar((char)0);
                }else{
                    passwordText.setEchoChar('•');
                }
            }
        });


        //弹簧布局
        //usernameLabel
        Spring childWith = Spring.sum(Spring.sum(Spring.width(usernameLabel)
                , Spring.width(usernameText)), Spring.constant(20));
        int offsetX = childWith.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, centerPanel);
        //usernameText
        springLayout.putConstraint(SpringLayout.WEST, usernameText, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameText, 0, SpringLayout.NORTH, usernameLabel);

        //passwordLabel
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 40, SpringLayout.SOUTH, usernameLabel);
        //passwordTest
        springLayout.putConstraint(SpringLayout.WEST, passwordText, 20, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordText, 0, SpringLayout.NORTH, passwordLabel);

        //checkBox
        springLayout.putConstraint(SpringLayout.EAST, checkBox, 0, SpringLayout.EAST, passwordText);
        springLayout.putConstraint(SpringLayout.NORTH, checkBox, 40, SpringLayout.NORTH, passwordText);

        //loginBtn
        springLayout.putConstraint(SpringLayout.WEST, loginBtn, 60, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginBtn, 60, SpringLayout.SOUTH, passwordLabel);

        //enrollUserBtn
        springLayout.putConstraint(SpringLayout.WEST, enrollBtn, 70, SpringLayout.EAST, loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH, enrollBtn, 0, SpringLayout.NORTH, loginBtn);


        container.add(titleLabel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);



        URL resource = UserUI.class.getClassLoader().getResource("show.jpg");
        Image image = null;
        if (resource != null) {
            image = new ImageIcon(resource).getImage();
        }
        setIconImage(image);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }


}
