package com.cn.kevin.design.create.fantory.simple_factory.factory;

import com.cn.kevin.design.create.fantory.simple_factory.product.Benz;
import com.cn.kevin.design.create.fantory.simple_factory.product.Bmw;
import com.cn.kevin.design.create.fantory.simple_factory.product.Car;

/**
 * 简单车共产
 */
public class CarFactory {

    public enum CarTypeEnum{
        /**
         * 奔驰
         */
        BENZ,
        /**
         * 宝马
         */
        BMW,
        ;
    }


    public static Car getCar(CarTypeEnum carTypeEnum){
        switch (carTypeEnum){
            case BMW:
                return new Bmw();
            case BENZ:
                return new Benz();
            default:
                System.out.println("类型不对");
                return null;
        }
    }




}
