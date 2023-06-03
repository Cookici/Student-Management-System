package com.lls.sms.dao.impl;

import com.lls.sms.dao.BaseDao;
import com.lls.sms.dao.StudentDao;
import com.lls.sms.entity.Student;

import java.util.List;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.dao.impl
 * @ClassName: StudentDaoImpl
 * @Author: 63283
 * @Description: 实现类
 * @Date: 2023/5/29 19:06
 */

public class StudentDaoImpl extends BaseDao<Student> implements StudentDao {


    @Override
    public List<Student> selectStudentListByNameLike(String message) {
        return super.queryForList("select id,name,sex,age,major,math,computer from t_student where name like \"%\"?\"%\"", message);
    }


    @Override
    public Student selectStudent(Long id) {
        return super.queryForOne("select id,name,sex,age,major,math,computer from t_student where id=?", id);
    }


    @Override
    public int updateById(Long id, Student student) {
        return super.update("update t_student set name=?,sex=?,age=?,major=?,math=?,computer=? where id=?", student.getName(), student.getSex(), student.getAge(), student.getMajor(), student.getMath(), student.getComputer(), id);
    }

    @Override
    public int deleteById(Long id) {
        return super.update("delete from t_student where id=?", id);
    }

    @Override
    public int insertStudent(Student student) {
        return super.update("insert into t_student values(null,?,?,?,?,?,?)", student.getName(), student.getSex(), student.getAge(), student.getMajor(), student.getMath(), student.getComputer());
    }



}
