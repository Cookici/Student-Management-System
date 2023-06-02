package com.lls.sms.ui.dialog;

import com.lls.sms.service.StudentService;
import com.lls.sms.ui.StudentUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.dialog
 * @ClassName: DeleteStudentDialog
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/2 14:24
 */

public class DeleteStudentDialog extends JDialog{
    private JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
    private JLabel idLabel = new JLabel("学号：");
    private JTextField idText = new JTextField();

    private JButton deleteBtn = new JButton("删除");

    public DeleteStudentDialog(StudentUI studentUI, StudentService studentService) {

        super(studentUI, "删除", true);

        idLabel.setPreferredSize(new Dimension(80, 30));
        jPanel.add(idLabel);

        idText.setPreferredSize(new Dimension(200, 30));
        jPanel.add(idText);

        jPanel.add(deleteBtn);

        Container container = getContentPane();
        container.add(jPanel);



        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "你确定要删除这个学生🐎？", "确认操作", JOptionPane.YES_NO_OPTION);
                if(response==0){
                    studentService.deleteStudent(Long.valueOf(idText.getText()));
                    JOptionPane.showMessageDialog(null, "删除成功", "操作提示", JOptionPane.PLAIN_MESSAGE);
                }else if(response==1){
                    JOptionPane.showMessageDialog(null, "取消删除", "操作提示", JOptionPane.PLAIN_MESSAGE);
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
