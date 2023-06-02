package com.lls.sms.ui.custom;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.ui.custom
 * @ClassName: StudentTableCellRenderer
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/1 14:44
 */

public class StudentTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        //在每一列每一行显示之前都会调用
        if (row % 2 == 0) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
        //设置居中
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }

}
