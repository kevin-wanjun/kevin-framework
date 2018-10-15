package com.cn.kevin.design.create.fantory.simple_factory.product;

/**
 * 宝马车（具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class Bmw implements Car{
    @Override
    public void drive() {
        System.out.println("驾驶宝马车");
    }
}
