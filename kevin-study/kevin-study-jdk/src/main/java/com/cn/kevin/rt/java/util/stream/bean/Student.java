package com.cn.kevin.rt.java.util.stream.bean;

/**
 * 学生
 * @author wj
 * @date 2018-01-18
 */
public class Student {
    /**学生id*/
    private String id;
    /**学生名称*/
    private String studentName;
    /**班级id*/
    private String classId;
    /**班级名称*/
    private String className;

    public Student(String id, String studentName, String classId, String className) {
        this.id = id;
        this.studentName = studentName;
        this.classId = classId;
        this.className = className;
    }

    public Student(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

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

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", studentName='" + studentName + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
