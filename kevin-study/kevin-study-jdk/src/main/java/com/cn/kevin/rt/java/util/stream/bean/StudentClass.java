package com.cn.kevin.rt.java.util.stream.bean;

import java.util.List;

/**
 * 班级
 * @author wj
 * @date 2018-01-18
 */
public class StudentClass {
    /**班级id*/
    private String classId;
    /**班级名称*/
    private String className;
    /**学生集合*/
    private List<Student> students;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudentClass(String classId, String className, List<Student> students) {
        this.classId = classId;
        this.className = className;
        this.students = students;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", students=" + students.toString() +
                '}';
    }
}
