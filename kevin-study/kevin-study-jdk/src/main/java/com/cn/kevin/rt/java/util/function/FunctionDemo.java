package com.cn.kevin.rt.java.util.function;

import com.cn.kevin.rt.java.util.function.bean.InitData;
import com.cn.kevin.rt.java.util.function.bean.Student;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Funtion<T,R>是一个函数接口，其内定义了一个转换函数[R apply(T t)]，将T转换为R。比如Stream中的map方法便是接受该函数参数，
 * 将T转换为R。
 * @author wj
 * @date 2018-04-26
 */
public class FunctionDemo {

    static List<Student> DATA = InitData.DATA;

    public static void main(String[] args) {
        //compose();
        //andThen();
        identity();
    }

    /**
     * R apply(T t)
     * 转换函数，将T转换为R
     */
    public static void apply(){
        Function<Student,String> function = (Student student) -> student.getClassName();
        DATA.stream().map(function).forEach(System.out::println);

        Function<Student,Predicate<String>> function2 = (Student student) -> (String name) -> name.length() ==3;
        List<Predicate<String>> predicates = DATA.stream().map(function2).collect(Collectors.toList());
        predicates.forEach(e -> System.out.println(predicates.get(0) == e));
    }

    /**
     * default <V> Function<V, R> compose(Function<? super V, ? extends T> before)
     * 返回一个组合函数Function，首先执行before,然后再执行该Function 如果两个函数的求值都抛出异常，
     * 它将被中继到组合函数的调用者。如果before为null，将会抛出NullPointerException
     */
    public static void compose(){
        Function<Student,String> before =  e -> e.getClassName();
        Function<String,Integer> function = e -> e.length();
        Function<Student, Integer> function3 = function.compose(before);
        DATA.stream().map(function3).forEach(System.out::println);
    }

    /**
     * default <V> com.cn.kevin.jdk.Function<T, V> andThen(com.cn.kevin.jdk.Function<? super R, ? extends V> after)
     * 返回一个组合函数Function，首先执行Function,然后再执行after .如果两个函数的求值都抛出异常，它将被中继到组合函数的调用者。
     * 如果after为null，将会抛出NullPointerException
     */
    public static void andThen(){
        Function<String,Integer> function = e -> e.length();
        Function<Student,String> after =  e -> e.getClassName();

        Function<Student, Integer> function3 = after.andThen(function);
        DATA.stream().map(function3).forEach(System.out::println);
    }

    /**
     * static <T> Function<T, T> identity()
     * 将输入参数返回的函数
     */

    public static void identity(){
        Function<Object, Object> function = Function.identity();
    }


}
