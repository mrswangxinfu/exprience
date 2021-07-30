package java8.wxf.gof.createType.factoryMethod;


import java8.wxf.gof.createType.factory.Product;

/**
 * 工厂方法模式
 * 由子类创建对象
 *
 * 根据需求分出多个子工厂单独实现
 */
public abstract class Factory {
    abstract public Product factoryMethod();
    public Product doSomething(){
        Product product =factoryMethod();
        return product;
    }
}
