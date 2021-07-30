package java8.wxf.gof.createType.AbstractFactory;

/**
 * 抽象工厂方法
 * 创建家族对象由子类实现
 *
 * 与构建者模式区别：
 *     抽象工厂是直接生产配套的产品（比如家具）
 *     构建者模式是构建复杂对象与构建构成复杂对象的子组件是分开构建的，先把子组件构建，再装配成复杂对象
 */
 public abstract class AbstractFactory {
    public abstract AbstractProductA createProductA();
    public abstract AbstractProductB createProductB();

}
