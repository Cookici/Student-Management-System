package com.lls.sms.ui;


import com.lls.sms.entity.Student;
import com.lls.sms.service.StudentService;
import com.lls.sms.service.impl.StudentServiceImpl;
import com.lls.sms.ui.custom.StudentTable;
import com.lls.sms.ui.custom.StudentTableModel;
import com.lls.sms.ui.dialog.AddStudentDialog;
import com.lls.sms.ui.dialog.DeleteStudentDialog;
import com.lls.sms.ui.dialog.UpdateStudentDialog;
import com.lls.sms.utils.DimensionUtil;
import com.lls.sms.utils.TransformUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.List;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui
 * @ClassName: StudentUi
 * @Author: 63283
 * @Description: 学生界面
 * @Date: 2023/5/30 22:59
 */

public class StudentUI extends JFrame {

    private static StudentService studentService = new StudentServiceImpl();
    private static Integer currentPage = 1;
    private static String authority = "user";
    private static String message = "";

    private static String subject = "总分";
    private static String sort = "升序";

    private static Integer totalPageNum = 0;

    Map<Integer, List<Student>> searchStudentListAndPage = new HashMap<>();

    private static final Integer TOTAL_STUDENTS = studentService.studentsNum();

    private static Vector<Vector<Object>> noTransData = new Vector<>();

    private JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JButton logout = new JButton("注销");
    private JButton addBtn = new JButton("添加");
    private JButton updateBtn = new JButton("修改");
    private JButton deleteBtn = new JButton("删除");
    private JTextField searchText = new JTextField(15);
    private JButton searchBtn = new JButton("查询");

    private JPanel subjectPanel = new JPanel();
    private JLabel groupSubjectLabel = new JLabel("学科类型:");
    private JRadioButton mathRadio = new JRadioButton("数学");
    private JRadioButton computerRadio = new JRadioButton("计算机");
    private JRadioButton tatolRadio = new JRadioButton("总分", true);
    private ButtonGroup groupSuject = new ButtonGroup();

    private JPanel sortPanel = new JPanel();
    private JLabel groupSortLabel = new JLabel("排序类型:");
    private JRadioButton upRadio = new JRadioButton("升序", true);
    private JRadioButton downRadio = new JRadioButton("降序");
    private JRadioButton originalRadio = new JRadioButton("原");
    private ButtonGroup groupSort = new ButtonGroup();


    private JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JButton firstBtn = new JButton("首页");
    private JButton preBtn = new JButton("上一页");
    private JLabel pageLabel = new JLabel();
    private JButton nextBtn = new JButton("下一页");
    private JButton lastBtn = new JButton("末页");

    private JTextField pageText = new JTextField();
    private JButton pageBtn = new JButton("跳转");
    private StudentTable studentTable = new StudentTable();

    SystemTray systemTray;
    TrayIcon trayIcon;

    public StudentUI(String authority) {
        super("学生管理系统");

        StudentUI.authority = authority;

        Container container = getContentPane();

        //初始化操作组件
        initLayoutNorth(container);

        subject = getSelectText(subjectPanel);

        sort = getSelectText(sortPanel);


        //设置JTable
        initLayoutCenter(subject, sort, container);

        //初始化页数组件
        initLayoutSouth(container);


        pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");

        //注销
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(null, "你确定要退出当前账户🐎？", "确认注销操作", JOptionPane.YES_NO_OPTION);
                if(response==0){
                    JOptionPane.showMessageDialog(null, "成功退出", "操作提示", JOptionPane.PLAIN_MESSAGE);
                    totalPageNum = 0;
                    currentPage = 1;
                    StudentUI.this.dispose();
                    new UserUI();
                }else if(response==1){
                    JOptionPane.showMessageDialog(null, "取消退出", "操作提示", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        //下一页
        nextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                currentPage++;
                if (currentPage <= totalPageNum) {
                    pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                    setTableData(currentPage, noTransData);
                } else {
                    currentPage = totalPageNum;
                    JOptionPane.showMessageDialog(null, "没有下一页", "操作错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //上一页
        preBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                currentPage--;
                if (currentPage >= 1) {
                    pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                    setTableData(currentPage, noTransData);
                } else {
                    currentPage = 1;
                    JOptionPane.showMessageDialog(null, "没有上一页", "操作错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //模糊查询
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                message = searchText.getText();
                searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);
                totalPageNum = 0;
                for (Map.Entry<Integer, List<Student>> entry : searchStudentListAndPage.entrySet()) {
                    totalPageNum++;
                }
                if (totalPageNum != 0) {
                    pageLabel.setText("当前第" + 1 + "页" + "/共有 " + totalPageNum + "页");
                    currentPage = 1;
                    setTableData(1, noTransData);
                } else {
                    totalPageNum = 1;
                    currentPage = 1;
                    DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
                    model.setRowCount(0); // 将行数设置为0，清除表格数据
                    model.fireTableDataChanged();
                    studentTable.setModel(model);
                    pageLabel.setText("当前第" + 1 + "页" + "/共有 " + 1 + "页");
                    JOptionPane.showMessageDialog(null, "没有查询到用户", "提示", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });


        //学科排序选择
        ActionListener actionListenerSubject = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
                subject = selectedRadioButton.getText();
                searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);
                currentPage = 1;
                pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                setTableData(1, noTransData);
            }
        };
        mathRadio.addActionListener(actionListenerSubject);
        computerRadio.addActionListener(actionListenerSubject);
        tatolRadio.addActionListener(actionListenerSubject);


        //成绩排序选择
        ActionListener actionListenerSort = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
                sort = selectedRadioButton.getText();
                searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);
                currentPage = 1;
                pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                setTableData(1, noTransData);
            }
        };
        upRadio.addActionListener(actionListenerSort);
        downRadio.addActionListener(actionListenerSort);
        originalRadio.addActionListener(actionListenerSort);


        //添
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudentDialog(StudentUI.this,studentService);
                noTransData.clear();
                totalPageNum = 0;
                searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);
                for (Map.Entry<Integer, List<Student>> entry : searchStudentListAndPage.entrySet()) {
                    totalPageNum++;
                }
                currentPage = totalPageNum;
                pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                setTableData(currentPage,noTransData);
            }
        });


        //改
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStudentDialog(StudentUI.this,studentService);
                noTransData.clear();
                totalPageNum = 0;
                searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);
                for (Map.Entry<Integer, List<Student>> entry : searchStudentListAndPage.entrySet()) {
                    totalPageNum++;
                }
                currentPage = totalPageNum;
                setTableData(currentPage,noTransData);
            }

        });


        //删
        deleteBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeleteStudentDialog(StudentUI.this,studentService);
                noTransData.clear();
                totalPageNum = 0;
                searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);
                for (Map.Entry<Integer, List<Student>> entry : searchStudentListAndPage.entrySet()) {
                    totalPageNum++;
                }
                currentPage = totalPageNum;
                pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                setTableData(currentPage,noTransData);
            }
        });


        //首页
        firstBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                currentPage = 1;
                pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                setTableData(currentPage,noTransData);
            }
        });


        //末页
        lastBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noTransData.clear();
                pageLabel.setText("当前第" + totalPageNum + "页" + "/共有 " + totalPageNum + "页");
                setTableData(totalPageNum,noTransData);
            }
        });


        //跳转
        pageText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if(!(keyChar >= '0' && keyChar <= '9')){
                    e.consume();
                }
            }
        });

        pageBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Integer.parseInt(pageText.getText()) > totalPageNum) {
                    JOptionPane.showMessageDialog(null, "输入页数超过总页数", "操作错误", JOptionPane.ERROR_MESSAGE);
                } else if (Integer.parseInt(pageText.getText())<1) {
                    JOptionPane.showMessageDialog(null, "输入页数小于1", "操作错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    if ("".equals(pageText.getText())) {
                        noTransData.clear();
                        currentPage = 1;
                        pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                        setTableData(currentPage, noTransData);
                    } else {
                        noTransData.clear();
                        currentPage = Integer.valueOf(pageText.getText());
                        pageLabel.setText("当前第" + currentPage + "页" + "/共有 " + totalPageNum + "页");
                        setTableData(currentPage, noTransData);
                    }
                }
            }
        });


        //设置图标和界面大小
        iconAndInterfaceSizeSetting();

    }

    private void setTableData(Integer currentPage, Vector<Vector<Object>> noTransData) {
        Vector<Vector<Object>> data = TransformUtil.listToVector(noTransData, searchStudentListAndPage.get(currentPage - 1));
        DefaultTableModel studentTableModel = StudentTableModel.assembleTableModel(data);
        studentTable.setModel(studentTableModel);
        studentTable.renderRuler();
    }

    private void initLayoutCenter(String subject, String sort, Container container) {

        searchStudentListAndPage = studentService.getSearchStudentListAndPage(message, subject, sort);

        Vector<Vector<Object>> data = TransformUtil.listToVector(noTransData, searchStudentListAndPage.get(0));

        for (Map.Entry<Integer, List<Student>> entry : searchStudentListAndPage.entrySet()) {
            totalPageNum++;
        }

        //用自定义的DefaultTableModel封装数据
        DefaultTableModel studentTableModel = StudentTableModel.assembleTableModel(data);
        //设置DefaultTableModel
        studentTable.setModel(studentTableModel);
        //渲染JTable
        studentTable.renderRuler();
        //滚动面板
        //放在JScrollPane上才会显示列头
        JScrollPane jScrollPane = new JScrollPane(studentTable);
        container.add(jScrollPane, BorderLayout.CENTER);
    }

    private void initLayoutSouth(Container container) {
        southPanel.add(firstBtn);
        southPanel.add(preBtn);
        southPanel.add(pageLabel);
        southPanel.add(nextBtn);
        southPanel.add(lastBtn);
        pageText.setPreferredSize(new Dimension(30, 20));
        southPanel.add(pageText);
        southPanel.add(pageBtn);
        container.add(southPanel, BorderLayout.SOUTH);
    }


    private void initLayoutNorth(Container container) {
        northPanel.add(logout);
        if ("admin".equals(authority)) {
            northPanel.add(addBtn);
            northPanel.add(updateBtn);
            northPanel.add(deleteBtn);
        }
        northPanel.add(searchText);
        northPanel.add(searchBtn);

        groupSuject.add(mathRadio);
        groupSuject.add(computerRadio);
        groupSuject.add(tatolRadio);
        subjectPanel.add(groupSubjectLabel);
        subjectPanel.add(mathRadio);
        subjectPanel.add(computerRadio);
        subjectPanel.add(tatolRadio);
        northPanel.add(subjectPanel);

        groupSort.add(upRadio);
        groupSort.add(downRadio);
        groupSort.add(originalRadio);
        sortPanel.add(groupSortLabel);
        sortPanel.add(upRadio);
        sortPanel.add(downRadio);
        sortPanel.add(originalRadio);
        northPanel.add(sortPanel);

        container.add(northPanel, BorderLayout.NORTH);
    }


    private void iconAndInterfaceSizeSetting() {


        URL resource = StudentUI.class.getClassLoader().getResource("show.jpg");
        Image image = null;
        if (resource != null) {
            image = new ImageIcon(resource).getImage();
        }
        setIconImage(image);

        setBounds(DimensionUtil.getBounds());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    public String getSelectText(JPanel jPanel) {
        StringBuilder info = new StringBuilder();
        //通过面板属性名获取到该面板上的所有组件
        System.out.println(info);
        for (Component c : jPanel.getComponents()) {
            if (c instanceof JRadioButton) {
                if (((JRadioButton) c).isSelected()) {
                    info.append(((JRadioButton) c).getText());
                }
            }
        }
        return info.toString();
    }

}



