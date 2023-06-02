package com.lls.sms.service.impl;

import com.lls.sms.dao.StudentDao;
import com.lls.sms.dao.impl.StudentDaoImpl;
import com.lls.sms.entity.Student;
import com.lls.sms.service.StudentService;
import com.lls.sms.utils.JdbcUtil;
import com.lls.sms.utils.SortUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.service.impl
 * @ClassName: StudentServiceImpl
 * @Author: 63283
 * @Description: 实现类
 * @Date: 2023/5/30 16:30
 */

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public Map<Integer, List<Student>> getSearchStudentListAndPage(String msg, String subject, String order) {
        List<Student> studentList = null;
        Integer pageNum = null;
        Map<Integer, List<Student>> pageAndStudentList = new HashMap<>();
        try {
            studentList = studentDao.selectStudentListByNameLike(msg);
            studentList = SortUtil.sortStudentListByOrder(studentList, subject, order);
            if (studentList.size() % 22 == 0) {
                pageNum = studentList.size() / 22;
            } else {
                pageNum = studentList.size() / 22 + 1;
            }

            for (int i = 0; i < pageNum; i++) {
                int fromIndex = i * 22;
                int toIndex = Math.min((i + 1) * 22, studentList.size());

                List<Student> group = studentList.subList(fromIndex, toIndex);
                pageAndStudentList.put(i, group);
            }
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return pageAndStudentList;
    }

    @Override
    public Integer studentsNum() {
        List<Student> studentList = null;
        int total = 0;
        try {
            studentList = studentDao.getTotalStudent();
            total = studentList.size();
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return total;
    }


    @Override
    public Student getStudent(Long id) {
        Student student = null;
        try {
            student = studentDao.selectStudent(id);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return student;
    }

    @Override
    public int updateStudent(Long id, Student student) {
        int i = 0;
        try {
            i = studentDao.updateById(id, student);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return i;
    }

    @Override
    public int deleteStudent(Long id) {
        int i = 0;
        try {
            i = studentDao.deleteById(id);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return i;
    }

    @Override
    public int insertStudent(Student student) {
        int i = 0;
        try {
            i = studentDao.insertStudent(student);
        } catch (Exception e) {
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        JdbcUtil.commitAndClose();
        return i;
    }
}
