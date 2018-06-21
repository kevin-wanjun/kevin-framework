package com.cn.kevin.design.behavioral.template_method;

/**
 * 抽象出来的汽车类
 * @author wj
 * @Description 抽象出来的汽车类
 * @date 2016-01-15
 */
public abstract class AbstractCarModel {

    /**
     *  抽象类定义整个流程骨架
     *  最重要的是，汽车要能行驶！而且行驶流程是固定的，不能改变！
     */
     protected final void run(){
         System.out.println(this.getClass());
        //启动
        this.start();
        //鸣笛
        this.doAlarm();
        //到达目的地就停车
        this.stop();
    }

    /**
     * 启动
     */
    protected abstract void start();

    /**
     * 停车
     */
    protected abstract void stop();

    /**
     * 钩子方法，喇叭会响
     * 一般没有方法的实现，
     */
    protected void doAlarm(){
        //一般汽车喇叭都是“滴滴滴”哦，要想“巴拉拉”或者“啪啪啪”去实现类自己重写去！
        System.out.println("滴滴滴滴滴滴");
    }

}
