package com.cn.kevin.design.create.fantory.factory_method;

import com.cn.kevin.design.create.fantory.factory_method.factory.BulbLightFactory;
import com.cn.kevin.design.create.fantory.factory_method.factory.LightFactory;
import com.cn.kevin.design.create.fantory.factory_method.factory.TubeLightFactory;

/**
 * 客户测试
 * @author wj
 * @date 2015-01-13
 */
public class Client {

    public static void main(String[] args) {
        LightFactory bulbLightFactory = new BulbLightFactory();
        bulbLightFactory.getLight().turnOff();

        LightFactory tubeLightFactory = new TubeLightFactory();
        tubeLightFactory.getLight().turnOn();
    }

}
