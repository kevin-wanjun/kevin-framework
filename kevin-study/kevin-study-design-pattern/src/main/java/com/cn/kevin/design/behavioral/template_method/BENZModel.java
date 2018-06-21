package com.cn.kevin.design.behavioral.template_method;

/**
 * 奔驰汽车
 * @author wj
 * @Description 奔驰汽车
 * @date 2016-01-15
 */
public class BENZModel extends AbstractCarModel {
    /**
     * 实现汽车抽象模板类的start()方法，使奔驰能够启动
     */
    @Override
    protected void start() {
        System.out.println("奔驰启动！");
    }

    /**
     * 实现汽车抽象模板类stop()方法，使奔驰能够刹车
     */
    @Override
    protected void stop() {
        System.out.println("奔驰停车！");
    }

    /**
     * 奔驰汽车喇叭响声就是“巴拉巴拉巴拉”，需要重写父类doAlarm()方法
     */
    @Override
    protected void doAlarm(){
        System.out.println("奔驰鸣笛-》》巴拉巴拉巴拉");
    }
}
