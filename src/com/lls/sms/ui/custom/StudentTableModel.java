package com.lls.sms.ui.custom;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.customTableModel
 * @ClassName: studentTabaleModel
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/1 14:29
 */

public class StudentTableModel extends DefaultTableModel {

    private static StudentTableModel studentTableModel = new StudentTableModel();
    static Vector<String> columns = new Vector<>();

    static {
        columns.addElement("序号");
        columns.addElement("学号");
        columns.addElement("姓名");
        columns.addElement("性别");
        columns.addElement("年龄");
        columns.addElement("专业");
        columns.addElement("数学");
        columns.addElement("计算机");
        columns.addElement("总分");
    }

    private StudentTableModel(){
        super(null,columns);
    }

    public static StudentTableModel assembleTableModel(Vector<Vector<Object>> data){
        studentTableModel.setDataVector(data,columns);
        return studentTableModel;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


    public static Vector<String> getColumns() {
        return columns;
    }

}

