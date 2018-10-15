package com.cn.kevin.design.create.fantory.abstract_factory.product;

/**
 * 索尼耳机 （具体产品）
 * @author wj
 * @date 2015-01-13
 */
public class SonyHeadsetProduct implements HeadsetProduct{

    @Override
    public void listenMusic() {
        System.out.println("使用索尼耳机听歌");
    }
}
