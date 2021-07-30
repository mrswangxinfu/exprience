装饰器模式（Decorator）

效果
1. 又称包装器模式（Wrapper）。

2. 动态的为一个对象增加新的功能。

3. 装饰器模式是一种用于代替继承的技术，无须通过继承增加子类就能扩展对象的新功能。使用对象的关联关系代替继承关系，更加灵活，同时避免类型体系的快速膨胀。

4. 装饰器模式降低系统的耦合度，可以动态的增加或删除对象的职责，并使得需要装饰的具体构件类和具体装饰类可以独立变化，以便增加新的具体构件类和具体装饰类。




核心角色
1. 抽象构件角色（Component）：具体构件角色和装饰角色有相同的接口，客户端能够以与具体构件角色相同的方式同装饰角色交互。

2. 具体构件角色（ConcreteComponent）。

3. 装饰角色（Decorator）：持有一个抽象构件角色引用，接收所有客户端请求，并把这些请求转发给具体装饰角色。

4. 具体装饰角色（ConcreteDecorator）：给具体构件（原始构件）增加新功能。

  

  代码

   1. ICar.java
   
   
       //抽象构件角色--车
     1 public interface ICar {
     2 
     3     void move();
     4     
     5 }
     6 
     7 // 具体构件角色--普通车
     8 class Car implements ICar {
     9 
    10     @Override
    11     public void move() {
    12         System.out.println("陆地上跑！");
    13     }
    14     
    15 }
    16 
    17 // 抽象装饰角色--在普通车上添加功能的超级车
    18 class SuperCar implements ICar {
    19 
    20     private ICar car;
    21     
    22     public SuperCar(ICar car) {
    23         this.car = car;
    24     }
    25 
    26     public void move(){
               car.move();
           }
    27     
    28 }
    29 // 具体修饰角色
    30 class FlyCar extends SuperCar {
    31 
    32     public FlyCar(ICar car) {
    33         super(car);
    34     }
    35     
    36     @Override
    37     public void move() {
    38         super.move();
    39         fly();
    40     }
    41     
    42     public void fly() {
    43         System.out.println("天上飞！");
    44     }
    45     
    46 }
    47 // 具体修饰角色
    48 class WaterCar extends SuperCar {
    49     
    50     public WaterCar(ICar car) {
    51         super(car);
    52     }
    53 
    54     @Override
    55     public void move() {
    56         super.move();
    57         swim();
    58     }
    59     
    60     public void swim() {
    61         System.out.println("水上游！");
    62     }
    63     
    64 }
    65 // 具体修饰角色
    66 class AICar extends SuperCar {
    67 
    68     public AICar(ICar car) {
    69         super(car);
    70     }
    71 
    72     @Override
    73     public void move() {
    74         super.move();
    75         autoMove();
    76     }
    77     
    78     public void autoMove() {
    79         System.out.println("自动跑！");
    80     }
    81     
    82 }


   2. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         Car car = new Car();
     5         car.move();
     6         
     7         // 陆地上跑 + 天上飞
     8         FlyCar flyCar = new FlyCar(car);
     9         flyCar.move();
    10         
    11         // 陆地上跑  + 水上游
    12         WaterCar waterCar = new WaterCar(car);
    13         waterCar.move();
    14         
    15         // 陆地上跑  + 自动跑
    16         AICar aiCar = new AICar(car);
    17         aiCar.move();
    18         
    19         // 陆地上跑 + 天上飞 + 水上游
    20         WaterCar flyWaterCar = new WaterCar(flyCar);
    21         flyWaterCar.move();
    22         
    23         // 陆地上跑 + 天上飞 + 水上游 + 自动跑
    24         AICar aiFlyWaterCar = new AICar(flyWaterCar);
    25         aiFlyWaterCar.move();
    26     }
    27 
    28 }


应用场景
1. IO的InputStream、OutputStream、Reader、Writer的设计。

2. Servlet API的request对象的默认实现类HttpServletRequestWrapper。

