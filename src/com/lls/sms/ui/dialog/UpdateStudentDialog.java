package com.lls.sms.ui.dialog;

import com.lls.sms.entity.Student;
import com.lls.sms.service.StudentService;
import com.lls.sms.ui.StudentUI;
import com.mysql.cj.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.dialog
 * @ClassName: UpdateStudentDialog
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/2 14:21
 */

public class UpdateStudentDialog extends JDialog {
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    private JLabel idLabel = new JLabel("学号：");
    private JTextField idText = new JTextField();
    private JLabel nameLabel = new JLabel("姓名：");
    private JTextField nameText = new JTextField();
    private JLabel sexLabel = new JLabel("性别：");
    private JTextField sexText = new JTextField();
    private JLabel ageLabel = new JLabel("年龄：");
    private JTextField ageText = new JTextField();
    private JLabel majorLabel = new JLabel("专业：");
    private JTextField majorText = new JTextField();
    private JLabel mathLabel = new JLabel("数学成绩：");
    private JTextField mathText = new JTextField();
    private JLabel computerLabel = new JLabel("计算机成绩：");
    private JTextField computerText = new JTextField();

    private JButton updateBtn = new JButton("修改");

    public UpdateStudentDialog(StudentUI studentUI, StudentService studentService) {

        super(studentUI, "修改学生", true);

        idLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(idLabel);

        idText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(idText);

        nameLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(nameLabel);

        nameText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(nameText);

        sexLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(sexLabel);

        sexText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(sexText);

        ageLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(ageLabel);

        ageText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(ageText);

        majorLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(majorLabel);

        majorText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(majorText);

        mathLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(mathLabel);

        mathText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(mathText);

        computerLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(computerLabel);

        computerText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(computerText);

        jPanel.add(updateBtn);

        Container container = getContentPane();
        container.add(jPanel);


        updateBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Student student = studentService.getStudent(Long.valueOf(idText.getText()));
                    if (student.getName() == null) {
                        JOptionPane.showMessageDialog(null, "没有该学号的学生", "修改失败", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (
                                StringUtils.isNullOrEmpty(nameText.getText()) ||
                                        StringUtils.isNullOrEmpty(sexText.getText()) ||
                                        StringUtils.isNullOrEmpty(ageText.getText()) ||
                                        StringUtils.isNullOrEmpty(majorText.getText()) ||
                                        StringUtils.isNullOrEmpty(mathText.getText()) ||
                                        StringUtils.isNullOrEmpty(computerText.getText())
                        ) {
                            JOptionPane.showMessageDialog(null, "所有修改项不能为空", "修改失败", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (
                                    (Integer.parseInt(ageText.getText()) < 0 || Integer.parseInt(ageText.getText()) > 100) ||
                                            (Integer.parseInt(mathText.getText()) < 0 || Integer.parseInt(mathText.getText()) > 150) ||
                                            (Integer.parseInt(computerText.getText()) < 0 || Integer.parseInt(computerText.getText()) > 150)
                            ) {
                                JOptionPane.showMessageDialog(null, "年龄和成绩必须为数字或不合理", "修改失败", JOptionPane.ERROR_MESSAGE);
                            } else if (!("男".equals(sexText.getText()) || "女".equals(sexText.getText()))) {
                                JOptionPane.showMessageDialog(null, "性别不合法", "修改失败", JOptionPane.ERROR_MESSAGE);
                            } else {
                                Student newStudent = new Student(null, nameText.getText(), sexText.getText(), Integer.valueOf(ageText.getText()), majorText.getText(), Integer.valueOf(mathText.getText()), Integer.valueOf(computerText.getText()));
                                System.out.println(newStudent);
                                studentService.updateStudent(student.getId(), newStudent);
                                JOptionPane.showMessageDialog(null, "修改成功", "修改成功", JOptionPane.PLAIN_MESSAGE);
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "参数不和法", "修改失败", JOptionPane.ERROR_MESSAGE);
                    throw new RuntimeException(ex);
                } catch (RuntimeException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        setSize(350, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }
}
