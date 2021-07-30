package java8.wxf.gof.constructType.Decorator;

class SuperCar implements ICar{
    private ICar car;
    public SuperCar(ICar car) {
             this.car = car;
    }
    public void move() {
        car.move();
    }
}
