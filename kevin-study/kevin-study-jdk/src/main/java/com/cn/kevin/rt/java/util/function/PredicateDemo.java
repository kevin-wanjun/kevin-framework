package com.cn.kevin.rt.java.util.function;

import java.util.function.Predicate;

/**
 * java.util.function 包下相关知识 函数式接口[boolean test(T t)],用于返回boolean
 * @author wj
 * @date 2018-04-26
 */
public class PredicateDemo {

    public static void main(String[] args) {
        negate();
    }

    /**
     * boolean test(T t)
     * 泛型 参数 T ，返回 boolean
     */
    public static void test(){
        Predicate<String> predicate  = e -> e.startsWith("ss1");
        System.out.println(predicate.test("ssaaaaa"));
    }

    /**
     *  此方法返回一个Predicate<T>类型，意味着and方法可以像nodejs的then一样进行链式判断
     *  default Predicate<T> and(Predicate<? super T> other)
     */
    public static void and(){
        Predicate<String> predicate  = e -> e.length() > 3;
        Predicate<String> predicate2 = predicate.and(e -> e.startsWith("sss"));
        predicate2 = predicate2.and(e -> e.contains("r"));
        System.out.println(predicate2.test("ssss"));
    }

    /**
     * default Predicate<T> negate()
     * test 取反
     */
    public static void negate(){
        Predicate<String> predicate  = e -> e.startsWith("ss");
        //返回一个 Predicate 的表达式  (t) -> !test(t)
        Predicate<String> predicate2 = predicate.negate();

        System.out.println(predicate == predicate2);

        System.out.println(predicate2.test("aaaa"));
    }



    /**
     *  接受另一个Predicate方法作为参数，将参数方法和本身的test方法取或
     *  ddefault Predicate<T> or(Predicate<? super T> other)
     */
    public static void or(){
        Predicate<String> predicate  = e -> e.length() > 3;
        predicate = predicate.or(e -> e.contains("r"));
        System.out.println(predicate.test("ssss"));
    }

    /**
     * 首先它接受一个对象作为参数，返回一个Predicate类型的方法，如果参数targetRef为null，则直接返回Objects:null方法。
     * 这个方法有趣的地方在，它接受一个目标对象，返回的不是boolean值，而是一个方法（object -> targetRef.equals(object)）
     * static <T> Predicate<T> isEqual(Object targetRef)
     */
    public static void isEqual(){
        System.out.println(Predicate.isEqual("sss").test(111));
    }


}
