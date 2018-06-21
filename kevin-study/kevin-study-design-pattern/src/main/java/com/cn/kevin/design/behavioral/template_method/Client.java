package com.cn.kevin.design.behavioral.template_method;

/**
 * 实现场景类
 * @author wj
 * @Description 实现场景类
 * @date 2016-01-15
 */
public class Client {

    public static void main(String[] args){  
        //做个宝马
        AbstractCarModel bmw = new BMWModel();
        //宝马跑一跑
        bmw.run();  
        System.out.println("*************分割线*************");
        //做个奔驰
        AbstractCarModel benz = new BENZModel();
        //奔驰跑一跑  
        benz.run();
    }  
}  