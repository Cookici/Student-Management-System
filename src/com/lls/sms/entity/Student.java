package com.lls.sms.entity;

/**
 * @ProjectName: Student Management System
 * @Package: com.lls.sms.dao
 * @ClassName: Student
 * @Author: 63283
 * @Description: 学生实体类
 * @Date: 2023/5/29 18:46
 */

public class Student {

    private Long id;
    private String name;
    private String sex;
    private Integer age;
    private String major;
    private Integer math;
    private Integer computer;

    public Student() {
    }

    public Student(Long id, String name, String sex, Integer age, String major, Integer math, Integer computer) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.major = major;
        this.math = math;
        this.computer = computer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getMath() {
        return math;
    }

    public void setMath(Integer math) {
        this.math = math;
    }

    public Integer getComputer() {
        return computer;
    }

    public void setComputer(Integer computer) {
        this.computer = computer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", major='" + major + '\'' +
                ", math=" + math +
                ", computer=" + computer +
                '}';
    }
}
