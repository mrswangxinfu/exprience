package java8.wxf.gof.createType.factoryMethod;

import java8.wxf.gof.createType.factory.Product;

public class ConcreteFactory extends Factory  implements Product {


   public static Product concreteFactory=new ConcreteFactory();
    @Override
    public Product factoryMethod() {
     return concreteFactory;
//       return new ConcreteFactory();
    }
}
