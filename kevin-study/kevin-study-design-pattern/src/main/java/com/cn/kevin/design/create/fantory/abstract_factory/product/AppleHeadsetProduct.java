package com.cn.kevin.design.create.fantory.abstract_factory.product;

/**
 * 苹果耳机（具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class AppleHeadsetProduct implements HeadsetProduct{

    @Override
    public void listenMusic() {
        System.out.println("使用苹果耳机听歌");
    }
}
