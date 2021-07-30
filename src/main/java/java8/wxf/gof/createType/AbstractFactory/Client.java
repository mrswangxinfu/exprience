package java8.wxf.gof.createType.AbstractFactory;

public class Client {

    public static void main(String[] args) {
        AbstractFactory abstractFactory=new ConcreteFactory1();
        AbstractProductA productA1=abstractFactory.createProductA();
        AbstractProductB productB1=abstractFactory.createProductB();

        AbstractFactory abstractFactory2=new ConcreteFactory2();
        AbstractProductA productA2=abstractFactory2.createProductA();
        AbstractProductB productB2=abstractFactory2.createProductB();

    }
}
