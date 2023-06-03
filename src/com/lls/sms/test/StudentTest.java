package com.lls.sms.test;


import com.lls.sms.entity.Student;
import com.lls.sms.service.StudentService;
import com.lls.sms.service.impl.StudentServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.test
 * @ClassName: StudentTest
 * @Author: 63283
 * @Description: Student测试
 * @Date: 2023/5/29 19:29
 */

public class StudentTest {

    private StudentService studentService = new StudentServiceImpl();

    private static final Logger log = Logger.getLogger("日志");


    @Test
    public void testGetSearchStudentListAndPage() {
        int pageNum = 0;
        Map<Integer, List<Student>> searchStudentListAndPage = studentService.getSearchStudentListAndPage("", "", "");
        for (Map.Entry<Integer, List<Student>> integerListEntry : searchStudentListAndPage.entrySet()) {
            Integer key = integerListEntry.getKey();
            log.info(String.valueOf(key));
            List<Student> value = integerListEntry.getValue();
            log.info(value.toString());
            pageNum++;
        }
        Assert.assertNotEquals(0, pageNum);
        if (pageNum > 1) {
            List<Student> students = searchStudentListAndPage.get(pageNum - 2);
            Assert.assertEquals(22, students.size());
        }
    }

    @Test
    public void testGetStudent() {
        Student student = studentService.getStudent(1L);
        Assert.assertNotNull(student);
    }

    @Test
    public void testUpdateStudent() {
        Student student = new Student(null, "test1", "男", 11, "test1", 10, 45);
        int i = studentService.updateStudent(11L, student);
        Assert.assertNotEquals(0, i);
    }


    @Test
    public void testDeleteStudent() {
        int i = studentService.deleteStudent(49L);
        Assert.assertNotEquals(0, i);
    }

    @Test
    public void testInsertStudent() {
        Student student = new Student(null, "testInsert", "男", 11, "testInsert", 10, 45);
        int i = studentService.insertStudent(student);
        Assert.assertNotEquals(0, i);
    }


}
