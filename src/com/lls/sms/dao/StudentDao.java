package com.lls.sms.dao;

import com.lls.sms.entity.Student;

import java.util.List;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.dao
 * @ClassName: StudentDao
 * @Author: 63283
 * @Description: 接口
 * @Date: 2023/5/29 19:06
 */

public interface StudentDao {

    /**
     * 根据输入内容 模糊查询
     *
     * @param message
     */
    List<Student> selectStudentListByNameLike(String message);


    /**
     * 获取单个学生信息
     *
     * @return
     */
    Student selectStudent(Long id);

    /**
     * 根据id修改学生信息
     *
     * @param id
     */
    int updateById(Long id, Student student);

    /**
     * 根据id删除学生信息
     *
     * @param id
     */
    int deleteById(Long id);


    /**
     * 添加学生
     *
     * @param student
     */
    int insertStudent(Student student);


    /**
     * 获取所有学生
     *
     * @return
     */
    List<Student> getTotalStudent();


}
