package java8.wxf.gof.constructType.Decorator;

public class Car implements ICar {
    @Override
    public void move() {
        System.out.println("陆地上跑！");
    }
}
