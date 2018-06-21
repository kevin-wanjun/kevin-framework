package com.cn.kevin.rt.java.util;


import java.util.Optional;

/**
 *
 *
 * @author wj
 * @date 2018-06-15
 */
public class OptionalDemo {


    public static void main(String[] args) {
        Optional<Food>  optional = Optional.of(new Food());

        Optional<Food>  optional2 = Optional.empty();

        System.out.println(optional2.get());
    }







}

/**
 * 食物
 * @author wj
 * date 2018-06-15
 */
class Food{

    /**
     * 味道
     */
    private String taste;

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }
}
