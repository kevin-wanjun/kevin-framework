package com.cn.kevin.design.create.fantory.abstract_factory.factory;

import com.cn.kevin.design.create.fantory.abstract_factory.product.HeadsetProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.MobileProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.SonyHeadsetProduct;
import com.cn.kevin.design.create.fantory.abstract_factory.product.SonyMobileProduct;

/**
 * 索尼共产(具体工厂)
 * @author wj
 * @date 2015-01-1
 */
public class SonyFactory extends ElectronicProductFactory{

    /**
     * 私有构造器（）
     */
    private SonyFactory(){}

    private static volatile ElectronicProductFactory instance = null;

    protected static ElectronicProductFactory getInstance(){
        if(instance  == null){
            synchronized (SonyFactory.class) {
                if(instance  == null){
                    instance = new SonyFactory();
                }
            }
        }
        return instance;
    }

    @Override
    public HeadsetProduct productionHeadset() {
        return new SonyHeadsetProduct();
    }

    @Override
    public MobileProduct productionMobile() {
        return new SonyMobileProduct();
    }
}
