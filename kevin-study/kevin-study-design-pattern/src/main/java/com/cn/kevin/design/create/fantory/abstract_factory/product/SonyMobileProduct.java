package com.cn.kevin.design.create.fantory.abstract_factory.product;

/**
 * 索尼手机（具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class SonyMobileProduct implements MobileProduct{
    @Override
    public void call() {
        System.out.println("使用索尼手机打电话");
    }

    @Override
    public void charge() {
        System.out.println("索尼手机正在充电");
    }
}
