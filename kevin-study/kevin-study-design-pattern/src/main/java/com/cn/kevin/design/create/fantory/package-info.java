/**
 * 简单工厂、工厂方法、抽象工厂
 *
 * 产品等级结构与产品族（抽象概念角度一）：
 * 1.产品等级结构：产品等级结构即产品的继承结构，这里我们按照车的类别来划分产品等级结构，当然从交通工具的抽象概念来说，
 * 可以把所有的车抽象成一个产品等级结构。例如一个抽象类是商务车，其子类有奔驰商务车，宝马商务车，奥迪商务车；
 * 则抽象商务车与具体的品牌之间的车构成了一个产品等级结构，抽象商务车是父类，而具体品牌的商务车是其子类。这是从纵向的角度来看。
 * 2.产品族：在抽象工厂模式中，产品族是指由同一个工厂生产的，位于不同产品等级结构中的一组产品，
 * 例如奔驰车工厂生产的商务车，跑车，奔驰商务车位于商务车产品等级结构中，奔驰跑车位于跑车产品结构中，
 * 奔驰商务车、奔驰跑车构成了一个产品族。这是从横向的角度来看。
 *
 * 产品等级结构与产品族（抽象概念角度二）：
 * 1.产品等级结构：产品等级结构即产品的继承结构，如一个抽象类是电视机，其子类有海尔电视机、海信电视机、TCL电视机，
 * 则抽象电视机与具体品牌的电视机之间构成了一个产品等级结构，抽象电视机是父类，而具体品牌的电视机是其子类。
 * 2.产品族：在抽象工厂模式中，产品族是指由同一个工厂生产的，位于不同产品等级结构中的一组产品。
 * 如海尔电器工厂生产的海尔电视机、海尔电冰箱，海尔电视机位于电视机产品等级结构中，海尔电冰箱位于电冰箱产品等级结构中。
 * 海尔电视机、海尔电冰箱构成了一个产品族。
 *
 * @author wj
 * @Description 简单工厂、工厂方法、抽象工厂
 * @date 2015-01-12
 */
package com.cn.kevin.design.create.fantory;