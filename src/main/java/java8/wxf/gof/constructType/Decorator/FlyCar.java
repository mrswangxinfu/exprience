package java8.wxf.gof.constructType.Decorator;

public class FlyCar extends SuperCar {
    public FlyCar(ICar car) {
        super(car);
    }
    // 制造栈溢出
    public FlyCar() {
        super(new FlyCar());
    }
    @Override
    public void move() {
        super.move();
        fly();
    }
    public void fly(){
        System.out.println("天上飞！");
    }
}
