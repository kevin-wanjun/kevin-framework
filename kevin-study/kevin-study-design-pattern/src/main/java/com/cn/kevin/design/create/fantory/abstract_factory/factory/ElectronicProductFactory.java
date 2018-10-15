package com.cn.kevin.design.create.fantory.abstract_factory.factory;

import com.cn.kevin.design.create.fantory.abstract_factory.product.HeadsetProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.MobileProduct;

/**
 * 电子产品共产（抽象工厂）
 * @author wj
 * @date 2015-01-13
 */
public abstract class ElectronicProductFactory {


    public enum ElectronicProductTypeEnum{
        /**
         * 苹果
         */
        APPLE,
        /**
         * 索尼
         */
        SONY,
        ;
    }

    /**
     * 工厂获取
     * @return
     */
    public static ElectronicProductFactory getInstance(ElectronicProductTypeEnum typeEnum){
        switch (typeEnum){
            case APPLE :
                return AppleFactory.getInstance();
            case SONY :
                return SonyFactory.getInstance();
            default:
                System.out.println("请输入正确的类型!");
                return null;
        }
    }

    /**
     * 生产耳机
     * @return
     */
    public abstract HeadsetProduct productionHeadset();

    /**
     * 生产手机
     *
     * @return
     */
    public abstract MobileProduct productionMobile();
}
