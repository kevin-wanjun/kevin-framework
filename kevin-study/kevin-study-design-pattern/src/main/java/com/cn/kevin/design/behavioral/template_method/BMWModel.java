package com.cn.kevin.design.behavioral.template_method;


/**
 * 宝马汽车
 * @author wj
 * @Description 宝马汽车
 * @date 2016-01-15
 */
public class BMWModel extends AbstractCarModel {

    /**
     * 实现汽车抽象模板类的start()方法，使宝马能够启动
     */
    @Override
    protected void start() {
        System.out.println("宝马启动！");
    }

    /**
     * 实现汽车抽象模板类stop()方法，使宝马能够刹车
     */
    @Override
    protected void stop() {
        System.out.println("宝马停车！");
    }

    //宝马汽车喇叭响声就是“滴滴滴”，继承父类doAlarm()方法即可

}
