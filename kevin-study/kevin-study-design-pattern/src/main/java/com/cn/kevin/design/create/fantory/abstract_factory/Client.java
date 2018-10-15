package com.cn.kevin.design.create.fantory.abstract_factory;


import com.cn.kevin.design.create.fantory.abstract_factory.factory.ElectronicProductFactory;

/**
 * 客户测试
 * @author wj
 * @date 2015-01-13
 */
public class Client {

    public static void main(String[] args) {
        ElectronicProductFactory appleFactory =
                ElectronicProductFactory.getInstance(ElectronicProductFactory.ElectronicProductTypeEnum.APPLE);
        appleFactory.productionMobile().call();
        appleFactory.productionHeadset().listenMusic();


        ElectronicProductFactory sonyFactory =
                ElectronicProductFactory.getInstance(ElectronicProductFactory.ElectronicProductTypeEnum.SONY);
        sonyFactory.productionMobile().charge();
        sonyFactory.productionHeadset().listenMusic();
    }

}
