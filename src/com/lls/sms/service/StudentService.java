package com.lls.sms.service;

import com.lls.sms.entity.Student;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.service
 * @ClassName: service
 * @Author: 63283
 * @Description: 接口
 * @Date: 2023/5/30 16:11
 */

public interface StudentService {


    /**
     * 根据ui中的信息获取指定学生列表（搜索框 页码）
     * @param msg
     * @param order
     * @return
     */

    Map<Integer, List<Student>> getSearchStudentListAndPage(String msg, String subject, String order);


    /**
     * 根据学生id获取信息
     * @param id
     * @return
     */
    Student getStudent(Long id);

    /**
     * 根据学生id修改学生
     * @param id
     * @param student
     * @return
     */
    int updateStudent(Long id,Student student);

    /**
     * 根据学生id删除学生
     * @param id
     * @return
     */
    int deleteStudent(Long id);

    /**
     * 添加学生 自增主键
     * @param student
     * @return
     */
    int insertStudent(Student student);


}
