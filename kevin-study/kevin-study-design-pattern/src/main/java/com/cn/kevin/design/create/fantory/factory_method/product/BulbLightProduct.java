package com.cn.kevin.design.create.fantory.factory_method.product;

/**
 * 灯泡灯产品（具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class BulbLightProduct implements LightProduct{

    @Override
    public void turnOff() {
        System.out.println("打开灯泡灯");
    }

    @Override
    public void turnOn() {
        System.out.println("关掉灯泡灯");
    }
}
