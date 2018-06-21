package com.cn.kevin.rt.java.util.stream.bean;

import java.util.ArrayList;
import java.util.List;

public class InitData {

    public static List<Student> DATA = new ArrayList(){
        {
            add(new Student("0001","田虎子","100001","一年一班"));
            add(new Student("0002","蛟九","100001","一年一班"));
            add(new Student("0003","甘久珍","100001","一年一班"));
            add(new Student("0004","田园","100002","一年二班"));
            add(new Student("0005","大胡子","100002","一年二班"));
            add(new Student("0006","甄姬","100002","一年二班"));
            add(new Student("0007","苏安是全","100003","一年三班"));
            add(new Student("0008","关飞","100003","一年三班"));
            add(new Student("0009","校田径俺三","100004","一年四班"));
            add(new Student("0010","餐刀锋","100004","一年四班"));
        }

    };
}
