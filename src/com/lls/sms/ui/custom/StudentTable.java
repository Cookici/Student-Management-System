package com.lls.sms.ui.custom;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.custom
 * @ClassName: StudentTable
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/1 15:29
 */

public class StudentTable extends JTable {

    public StudentTable(){
        super();
        //设置表头
        JTableHeader tableHeader = this.getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        this.setFont(new Font(null, Font.PLAIN, 14));
        this.setForeground(Color.BLACK);
        this.setGridColor(Color.BLACK);
        this.setRowHeight(30);
        //设置多行选择
        this.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
    }


    public void renderRuler(){
        //渲染表格列的渲染方式
        Vector<String> columns = StudentTableModel.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = this.getColumn(columns.get(i));
            //StudentTableCellRenderer 主要逻辑
            column.setCellRenderer(new StudentTableCellRenderer());
            if(i == 0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                //宽度不可改变
                column.setResizable(false);
            }
        }
    }

}
