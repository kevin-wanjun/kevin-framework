package com.cn.kevin.design.create.fantory.simple_factory.product;

/**
 * 奔驰车（具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class Benz implements Car{
    @Override
    public void drive() {
        System.out.println("驾驶奔驰车");
    }

}
