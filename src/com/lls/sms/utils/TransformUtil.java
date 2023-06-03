package com.lls.sms.utils;

import com.lls.sms.entity.Student;

import java.util.List;
import java.util.Vector;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.utils
 * @ClassName: TransformUtil
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/6/1 16:51
 */

public class TransformUtil {

    private static Vector<Vector<Object>> data;
    private static List<Student> studentList;

    private TransformUtil() {
    }


    public static Vector<Vector<Object>> listToVector(Vector<Vector<Object>> data, List<Student> studentList) {
        TransformUtil.data = data;
        TransformUtil.studentList = studentList;
        if (studentList != null) {
            for (int i = 1; i <= studentList.size(); i++) {
                // 创建一个Vector<Object>对象来保存每个学生的姓名和性别等属性
                Vector<Object> studentVector = new Vector<>();
                studentVector.add(i);
                studentVector.add(studentList.get(i - 1).getId());
                studentVector.add(studentList.get(i - 1).getName());
                studentVector.add(studentList.get(i - 1).getSex());
                studentVector.add(studentList.get(i - 1).getAge());
                studentVector.add(studentList.get(i - 1).getMajor());
                studentVector.add(studentList.get(i - 1).getMath());
                studentVector.add(studentList.get(i - 1).getComputer());
                studentVector.add(studentList.get(i - 1).getComputer() + studentList.get(i - 1).getMath());
                // 将学生的Vector添加到外部Vector中
                data.add(studentVector);
            }
        }
        return data;
    }


}
