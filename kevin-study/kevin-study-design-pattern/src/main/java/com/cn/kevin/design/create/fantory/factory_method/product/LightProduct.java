package com.cn.kevin.design.create.fantory.factory_method.product;

/**
 * 灯产品(抽象产品)
 * @author wj
 * @date 2015-01-13
 */
public interface LightProduct {
    /**
     * 开灯
     */
    void turnOff();

    /**
     * 关灯
     */
    void turnOn();
}
