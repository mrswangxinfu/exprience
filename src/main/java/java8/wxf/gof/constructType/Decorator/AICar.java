package java8.wxf.gof.constructType.Decorator;

public class AICar extends SuperCar {
    public AICar(ICar car) {
        super(car);
    }

    @Override
    public void move() {
        super.move();
        autoMove();
    }
    public void autoMove() {
        System.out.println("自动跑");
    }
}
