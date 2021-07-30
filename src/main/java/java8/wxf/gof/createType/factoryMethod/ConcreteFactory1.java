package java8.wxf.gof.createType.factoryMethod;


import java8.wxf.gof.createType.factory.Product;

public class ConcreteFactory1 extends Factory  implements Product {

    @Override
    public Product factoryMethod() {
        return new ConcreteFactory1();
    }
}
