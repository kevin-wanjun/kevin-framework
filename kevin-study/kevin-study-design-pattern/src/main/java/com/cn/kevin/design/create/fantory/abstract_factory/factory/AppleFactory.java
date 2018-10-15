package com.cn.kevin.design.create.fantory.abstract_factory.factory;

import com.cn.kevin.design.create.fantory.abstract_factory.product.AppleHeadsetProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.AppleMobileProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.HeadsetProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.MobileProduct;

/**
 * 苹果工厂（具体工厂）
 * @author wj
 * @date 2015-01-13
 */
public class AppleFactory extends ElectronicProductFactory{

    /**
     * 私有构造器（）
     */
    private AppleFactory(){}

    private static volatile ElectronicProductFactory instance = null;

    public static ElectronicProductFactory getInstance(){
        if(instance  == null){
            synchronized (AppleFactory.class) {
                if(instance  == null){
                    instance = new AppleFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public HeadsetProduct productionHeadset() {
        return new AppleHeadsetProduct();
    }

    @Override
    public MobileProduct productionMobile() {
        return new AppleMobileProduct();
    }
}
