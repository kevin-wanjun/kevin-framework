package com.cn.kevin.design.create.fantory.factory_method.factory;

import com.cn.kevin.design.create.fantory.factory_method.product.LightProduct;

/**
 * 灯工厂(抽象工厂)
 * @author wj
 * @date 2015-01-13
 */
public interface LightFactory {
    /**
     * 获取点灯
     * @return
     */
    LightProduct getLight();

}
