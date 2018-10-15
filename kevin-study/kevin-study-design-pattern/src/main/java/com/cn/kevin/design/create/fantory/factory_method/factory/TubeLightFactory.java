package com.cn.kevin.design.create.fantory.factory_method.factory;

import com.cn.kevin.design.create.fantory.factory_method.product.LightProduct;
import com.cn.kevin.design.create.fantory.factory_method.product.TubeLightProduct;

/**
 * 管灯工厂（具体工厂）
 * @author wj
 * @date 2015-01-13
 */
public class TubeLightFactory implements LightFactory{
    @Override
    public LightProduct getLight() {
        return new TubeLightProduct();
    }
}
