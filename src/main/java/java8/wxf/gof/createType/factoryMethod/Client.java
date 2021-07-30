package java8.wxf.gof.createType.factoryMethod;


import java8.wxf.gof.createType.factory.Product;

public class Client {

    public static void main(String[] args) {
        Factory factory=new ConcreteFactory();
        Product product1= factory.doSomething();
        Product product=factory.factoryMethod();

        System.out.println(product);
        System.out.println(product1);
        System.out.println(product==product1);

    }
}
