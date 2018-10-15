package com.cn.kevin.design.create.fantory.simple_factory;

import com.cn.kevin.design.create.fantory.simple_factory.factory.CarFactory;
import com.cn.kevin.design.create.fantory.simple_factory.product.Car;

/**
 * 实现场景类
 * @author wj
 * @Description 实现场景类
 * @date 2015-01-12
 */
public class Client {

    public static void main(String[] args) {
        Car car = CarFactory.getCar(CarFactory.CarTypeEnum.BENZ);
        car.drive();
    }
}
