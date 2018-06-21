package com.cn.kevin.rt.java.util.function;


import com.cn.kevin.rt.java.util.function.bean.InitData;
import com.cn.kevin.rt.java.util.function.bean.Student;

import java.util.List;
import java.util.function.Consumer;

/**
 * Consumer和Predicate方法不同，
 * Predicate只是判断元素是否满足某种条件的函数接口，而Consumer是要对某元素进行操作的函数接口。
 * @author wj
 * @date 2018-04-27
 *
 */
public class ConsumerDemo {

    static List<Student> DATA = InitData.DATA;

    public static void main(String[] args) {
        accept();
    }

    /**
     * 方法接受一个参数，不返回结果。我们可以对参数进行处理，当然也可以不处理。
     *  void accept(T t);
     */
    public static void accept(){
        Consumer<Student> con = s -> s.setStudentName(s.getClassName()+"_"+s.getStudentName());
        DATA.forEach(con);
        DATA.forEach(student -> System.out.println(student.getStudentName()));
    }

    /**
     * andThen()方法为此接口添加链式处理。
     * default Consumer<T> andThen(Consumer<? super T> after)
     */
    public static void andThen(){
        Consumer<Student> con = s -> s.setStudentName(s.getClassName()+"_"+s.getStudentName());
        con = con.andThen(student -> student.setStudentName(student.getId()+":"+student.getStudentName()));
        DATA.forEach(con);
        DATA.forEach(student -> System.out.println(student.getStudentName()));
    }
}
