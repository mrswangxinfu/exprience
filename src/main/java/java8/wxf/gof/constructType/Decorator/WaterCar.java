package java8.wxf.gof.constructType.Decorator;

public class WaterCar extends SuperCar {
    public WaterCar(ICar car) {
        super(car);
    }

    @Override
    public void move() {
        super.move();
        waterMove();
    }
    public void waterMove() {
        System.out.println("水里游");
    }
}
