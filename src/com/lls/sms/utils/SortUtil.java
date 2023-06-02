package com.lls.sms.utils;

import com.lls.sms.entity.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.utils
 * @ClassName: SortUtil
 * @Author: 63283
 * @Description: TODO
 * @Date: 2023/5/30 17:34
 */

public class SortUtil {


    private SortUtil() {
    }


    public static List<Student> sortStudentListByOrder(List<Student> studentList, String subject, String order) {
        List<Student> sortList = studentList;
        if ("数学".equals(subject)) {
            if ("降序".equals(order)) {
                sortList = studentList.stream().sorted(Comparator.comparing(Student::getMath).reversed()).collect(Collectors.toList());
            } else if ("升序".equals(order)) {
                sortList = studentList.stream().sorted(Comparator.comparing(Student::getMath)).collect(Collectors.toList());
            }
        }
        if ("计算机".equals(subject)) {
            if ("降序".equals(order)) {
                sortList = studentList.stream().sorted(Comparator.comparing(Student::getComputer).reversed()).collect(Collectors.toList());
            } else if ("升序".equals(order)) {
                sortList = studentList.stream().sorted(Comparator.comparing(Student::getComputer)).collect(Collectors.toList());
            }
        }
        if ("总分".equals(subject)) {
            if ("降序".equals(order)) {
                sortList.sort((o1, o2) -> o1.getMath() + o1.getComputer() - o2.getMath() - o2.getComputer());
            } else if ("升序".equals(order)) {
                sortList.sort((o1, o2) -> o2.getMath() + o2.getComputer() - o1.getMath() - o1.getComputer());
            }
        }
        return sortList;
    }

}