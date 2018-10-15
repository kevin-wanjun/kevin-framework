/**
 * 抽象工厂模式（Abstract Factory）
 *
 *    抽象工厂模式是所有形态的工厂模式中最为抽象和最具一般性的一种形态。抽象工厂模式是指当有多个抽象角色时，使用的一种工厂模式。
 * 抽象工厂模式可以向客户端提供一个接口，使客户端在不必指定产品的具体的情况下，创建多个产品族中的产品对象。根据里氏替换原则，
 * 任何接受父类型的地方，都应当能够接受子类型。因此，实际上系统所需要的，仅仅是类型与这些抽象产品角色相同的一些实例，
 * 而不是这些抽象产品的实例。换言之，也就是这些抽象产品的具体子类的实例。工厂类负责创建抽象产品的具体子类的实例。
 *
 * 定义
 *   为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。
 *
 * 简介
 *      1.当每个抽象产品都有多于一个的具体子类的时候，工厂角色怎么知道实例化哪一个子类呢？比如每个抽象产品角色都有两个具体产品。
 *   抽象工厂模式提供两个具体工厂角色，分别对应于这两个具体产品角色，每一个具体工厂角色只负责某一个产品角色的实例化。
 *   每一个具体工厂类只负责创建抽象产品的某一个具体子类的实例。
 *
 *      2.每一个模式都是针对一定问题的解决方案，工厂方法模式针对的是一个产品等级结构；而抽象工厂模式针对的是多个产品等级结构。
 *
 * 产品族
 *      1.是指位于不同产品等级结构中，功能相关联的产品组成的家族。一般是位于不同的等级结构中的相同位置上。
 *  显然，每一个产品族中含有产品的数目，与产品等级结构的数目是相等的，形成一个二维的坐标系，水平坐标是产品等级结构，
 *  纵坐标是产品族。叫做相图。
 *
 *     2.当有多个不同的等级结构的产品时，如果使用工厂方法模式就势必要使用多个独立的工厂等级结构来对付这些产品的等级结构。
 *  如果这些产品等级结构是平行的，会导致多个平行的工厂等级结构。
 *
 *     3.抽象工厂模式使用同一个 工厂等级结构负责这些不同产品等级结构产品对象的创建。
 *
 *     4.对于每一个产品族，都有一个具体工厂。而每一个具体工厂创建属于同一个产品族，但是分属于不同等级结构的产品。
 *
 *     5.通过引进抽象工厂模式，可以处理具有相同（或者相似）等级结构的多个产品族中的产品对象的创建问题。
 *
 *     6.由于每个具体工厂角色都需要负责两个不同等级结构的产品对象的创建，因此每个工厂角色都需要提供两个工厂方法，
 *  分别用于创建两个等级结构的产品。既然每个具体工厂角色都需要实现这两个工厂方法，所以具有一般性，不妨抽象出来，
 *  移动到抽象工厂角色中加以声明。
 *
 *
 *      一般而言，有多少个产品等级结构，就会在工厂角色中发现多少个工厂方法。每一个产品等级结构中有多少个具体的产品，
 *  就有多少个产品族，也就会在工厂等级结构中发现多少个具体工厂。
 *
 *
 *  使用情况
 *          1.系统不依赖于产品类实例如何被创建，组合和表达的细节。
 *
 *          2.系统的产品有多于一个的产品族，而系统只消费其中某一族的产品（抽象工厂模式的原始用意Unix&Windows）
 *          Button--->UnixButton/WinButton
 *          Text----->UnixText/WinText
 *          Unix产品族和Windows产品族，不会同时使用。
 *          Factory--->UnixFactory/WinFactory
 *
 *          3.同属于同一个产品族是在一起使用的。这一约束必须在系统的设计中体现出来。
 *          4.系统提供一个产品类的库，所有产品以同样的接口出现，从而使客户端不依赖于实现。
 *
 *   优点：
 *      1.它分离了具体的类
 *      2.它使得易于交换产品系列
 *      3.它有利于产品的一致性
 *
 *   缺点：
 *      1.难以支持新种类的产品
 *      2.微型计算机配件，这个系统所需要的产品族有两个，一个系列是PC系列，另一个系列是MAC系列。
 *      3.产品等级结构也有两个，一个是RAM，一个是CPU。
 *
 *  //两个抽象产品
 *  public interface Cpu
 *  {
 *  }
 *  public interface Ram
 *  {
 *  }
 *
 * //四个具体产品
 * public class PcCpu implements Cpu
 * {
 * }
 * public class MacCpu implements Cpu
 * {
 * }
 * public class PcRam implements Ram
 * {
 * }
 * public class MacRam implements Ram
 * {
 * }
 *
 * //抽象工厂角色
 * public interface ComputerProducer
 * {
 *      Cpu createCpu();
 *      Ram createRam();
 * }
 *
 * //两个具体工厂角色
 * public class PcProducer implements ComputerProducer
 * {
 *      public Cpu createCpu()
 *      {
 *          return new PcCpu();
 *      }
 *
 *      public Ram createRam()
 *      {
 *          return new PcRam();
 *      }
 * }
 *
 * public class MacProducer implements ComputerProducer
 * {
 *      public Cpu createCpu()
 *      {
 *          return new MacCpu();
 *      }
 *
 *      public Ram createRam()
 *      {
 *          return new MacRam();
 *      }
 * }
 *
 *  一般情况下，有多少个抽象产品，就有多少个工厂方法。（比如再增加一个PC与MAC不同的其他计算机配件，例如显卡）。
 *
 *  OCP(开放-封闭原则（The Open-Close Principle，简称OCP）)
 *
 *  增加产品族。
 *  增加产品等级结构。
 *
 *  在不改变产品等级结构的情况下，增加产品族就是意味着向每一个产品等级结构中增加一个或者多个新的具体产品角色，
 *  这时只需要向工厂等级结构中增加新的元素就可以了，具体的说，只需要增加新的具体工厂类就可以了。
 *
 *  在产品族数目不变的情况下，增加产品等级结构，相当于增加一个与现有产品等级结构平行的一个新的产品等级结构，
 *  这时需要向修改所有的工厂角色，增加一个新的工厂方法,这是不支持OCP的。
 *
 *  Producer
 *      PcProducer MacProducer
 *  CPU
 *      PcCPU MacCPU
 *  RAM
 *      PcRAM MacCPU
 *
 *  在上面的结构中，增加产品族相当于增加一个新的厂商，比如Sun的CPU和RAM,这时，只需要增加一个SunProducer即可。
 *  而增加一个新的产品等级结构相当于增加一个显卡，而显卡也有Pc和Mac之分，那么对于所有的Producer，都需要增加一个方法：createCard()
 *
 *  其他关系
 *      与其他设计模式
 *
 *      单例模式：具体工厂类可以设计成单例类，一个单例类只有一个实例，它自己向外界提供自己唯一的实例。很显然，在农场系统中，
 *      只需要AppleFactory和SonyFactory的一个实例就可以了。而在计算机生产的例子中，PcProducer和RamProducer也分别只需要一个实例。
 *
 *      工厂的工厂：工厂角色与抽象产品角色合并（简单工厂模式java.util.DateFormat）,在抽象工厂模式中，
 *      抽象工厂类可以有静态方法，这个方法根据参数的值，返回对应的具体工厂类实例，但是其返回值类型是抽象工厂类型，
 *      这样可以在多态性的保证之下，允许静态工厂方法自行决定哪一个具体工厂符合要求。
 *
 *
 *
 *
 * @author wj
 * @Description 抽象工厂模式（Abstract Factory）
 * @date 2015-01-12
 */
package com.cn.kevin.design.create.fantory.abstract_factory;