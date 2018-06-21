package com.cn.kevin.rt.java.util.function;


import com.cn.kevin.rt.java.util.function.bean.Student;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Supplier< T>接口没有入参，返回一个T类型的对象，类似工厂方法。
 * @author wj
 * @date 2018-04-26
 */
public class SupplierDemo {

    public static void main(String[] args) {
        get();
    }

    /**
     * Supplier< T>接口没有入参，返回一个T类型的对象，类似工厂方法，每调用一次返回一个全新的 T 对象
     * T get();
     */
    public static void get(){
        Supplier<Student> studentSupplier = Student::new;
        //两次调用对象不相等
        System.out.println(studentSupplier.get() == studentSupplier.get());
    }


}
