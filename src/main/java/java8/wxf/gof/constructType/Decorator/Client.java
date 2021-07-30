package java8.wxf.gof.constructType.Decorator;

public class Client {
    public static void main(String[] args) {
         Car car = new Car();
         car.move();
         System.out.println("===================");
         // 陆地上跑 + 天上飞
         FlyCar flyCar = new FlyCar(car);
         flyCar.move();
         System.out.println("===================");
         // 陆地上跑  + 水上游
         WaterCar waterCar = new WaterCar(car);
         waterCar.move();
         System.out.println("===================");
        // 陆地上跑  + 自动跑
         AICar aiCar = new AICar(car);
         aiCar.move();
         System.out.println("===================");
        // 陆地上跑 + 天上飞 + 水上游
         WaterCar flyWaterCar = new WaterCar(flyCar);
         flyWaterCar.move();
         System.out.println("===================");
        // 陆地上跑 + 天上飞 + 水上游 + 自动跑
         AICar aiFlyWaterCar = new AICar(flyWaterCar);
         aiFlyWaterCar.move();
         System.out.println("===================");
         // 陆 飞
         FlyCar flyCar1 = new FlyCar(car);
         flyCar1.move();

    }
}
