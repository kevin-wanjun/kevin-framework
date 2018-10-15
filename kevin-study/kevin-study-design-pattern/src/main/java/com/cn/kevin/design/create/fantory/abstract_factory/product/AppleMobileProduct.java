package com.cn.kevin.design.create.fantory.abstract_factory.product;

/**
 * 苹果手机（具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class AppleMobileProduct implements MobileProduct{
    @Override
    public void call() {
        System.out.println("使用苹果手机打电话");
    }

    @Override
    public void charge() {
        System.out.println("苹果手机正在充电");
    }
}
