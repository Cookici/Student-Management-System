package com.lls.sms;

import com.lls.sms.ui.UserUI;
import com.lls.sms.utils.JdbcUtil;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms
 * @ClassName: Main
 * @Author: 63283
 * @Description: 入口
 * @Date: 2023/5/30 23:08
 */

public class Main {

    static {
        JdbcUtil.getConnection();
        JdbcUtil.commitAndClose();
    }

    public static void main(String[] args) {

        new UserUI();

    }


}
