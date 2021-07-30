工厂模式

效果
1. 实例化对象，用工厂方法代替new。实现了创建者和调用者的分离。

2. 将选择实现类、创建对象统一管理和控制，从而将调用者跟我们的实现类解耦。


分类
1. 简单工厂模式：(用一个工厂类生成不同的产品)用来产生同一等级结构中的任意产品。对于增加新的产品，需要修改已有代码。

2. 工厂方法模式：（先抽象一个工厂接口，不同的实现类作为生产不同产品的工厂）用来产生同一等级结构中的固定产品。支持增加任意产品。

3. 抽象工厂模式：（在工厂方法的基础上细化产品，将产品分离成许多组成部分，生产其组成部分（而构建者负责装配组件））用来生产不同产品族的全部产品。对于增加新的产品，无能为力；支持增加产品族。

4. 简单工厂模式效果：

    a) 又称静态工厂模式。

    b) 工厂类一般使用静态方法，通过接收的参数来返回不同的对象实例。

    c) 对于增加新产品只能修改代码（违反OCP）。

    d) 有两种实现方式（见代码）。

5. 工厂方法模式效果：

    a) 避免简单工厂的缺点，但不完全满足OCP。

    b) 简单工厂模式VS.工厂方法模式：

        i. 结构复杂度：显然简单工厂模式占优，简单工厂模式只要一个工厂，而工厂方法模式的工厂类随着产品类个数增加而增加。

        ii. 代码复杂度：代码复杂度与结构复杂度相反，简单工厂模式的工厂类随着产品增加需要增加更多方法（代码），而工厂方法模式每个具体工厂类只完成单一任务，代码简单。

        iii. 客户端编程难度：工厂方法模式虽然满足了OCP，但客户端编码中需要对工厂实例化，而简单工厂模式的工厂类是一个静态类。

        iv. 管理上的难度：工厂方法模式需要维护的工厂类过多，而简单工厂模式只有一个。

    c) 设计理论建议使用工厂方法模式，但实际中一般都用简单工厂模式。

6. 抽象工厂模式效果：

    a) 用来生产不同产品族的全部产品。对于增加新的产品，无能为力；支持增加产品族。

    b) 抽象工厂模式是工厂方法模式的升级版本，在有多个业务品种、业务分类时，通过抽象工厂模式产生需要的对象时一种非常好的解决方式。
    
   
   
  代码（简单工厂）
   
   
   1. Car.java
   
    1 public interface Car {
    2 
    3     void run();
    4     
    5 }
   
   
   2. Audi.java
   
    1 public class Audi implements Car {
    2 
    3     @Override
    4     public void run() {
    5         System.out.println("奥迪在跑！");
    6     }
    7 
    8 }


   3. Byd.java
   
    1 public class Byd implements Car {
    2 
    3     @Override
    4     public void run() {
    5         System.out.println("比亚迪在跑！");
    6     }
    7 
    8 }


   4. CarFactory.java
   
    1 // 一个方法实现
    2 public class CarFactory1 {
    3 
    4     public static Car createCar(String type) {
    5         if ("Audi".equals(type)) {
    6             return new Audi();
    7         }
    8         if ("Byd".equals(type)) {
    9             return new Byd();
    10         }
    11         return null;
    12     }
    13     
    14 }


   5. CarFactory2.java
   
    1 // 多个方法实现
    2 public class CarFactory2 {
    3     
    4     public static Car createAudi() {
    5         return new Audi();
    6     }
    7 
    8     public static Car createByd() {
    9         return new Byd();
    10     }
    11     
    12 }


   6. Client.java
   
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Car car1 = CarFactory1.createCar("Audi");
    5         Car car2 = CarFactory1.createCar("Byd");
    6         car1.run();
    7         car2.run();
    8     }
    9 
    10 }
    
    
  代码（工厂方法）
    
   1. Car.java
    
    1 public interface Car {
    2 
    3     void run();
    4     
    5 }
    
   2. CarFactory.java
    
    1 public interface CarFactory {
    2 
    3     Car createCar();
    4     
    5 }
    
   3. Audi.java
    
    1 public class Audi implements Car {
    2 
    3     @Override
    4     public void run() {
    5         System.out.println("奥迪在跑！");
    6     }
    7 
    8 }


   4. AudiFactory.java
    
    1 public class AudiFactory implements CarFactory {
    2 
    3     @Override
    4     public Car createCar() {
    5         return new Audi();
    6     }
    7 
    8 }

   
   5. Byd.java
    
    1 public class Byd implements Car {
    2 
    3     @Override
    4     public void run() {
    5         System.out.println("比亚迪在跑！");
    6     }
    7 
    8 }

   6. BydFactory.java

    1 public class BydFactory implements CarFactory {
    2 
    3     @Override
    4     public Car createCar() {
    5         return new Byd();
    6     }
    7 
    8 }

   7. Client.java
    
     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         Car car1 = new AudiFactory().createCar();
     5         Car car2 = new BydFactory().createCar();
     6         car1.run();
     7         car2.run();
     8     }
     9 
    10 }
    
    
  代码（抽象工厂）
   1. Engine.java
    
     1 public interface Engine {
     2 
     3     void start();
     4     
     5     void speedUp();
     6     
     7 }
     8 
     9 class LuxuryEngine implements Engine {
    10     
    11     @Override
    12     public void start() {
    13         System.out.println("快速启动！");
    14     }
    15     
    16     @Override
    17     public void speedUp() {
    18         System.out.println("快速加速！");
    19     }
    20     
    21 }
    22 
    23 class LowEngine implements Engine {
    24 
    25     @Override
    26     public void start() {
    27         System.out.println("慢速启动！");
    28     }
    29 
    30     @Override
    31     public void speedUp() {
    32         System.out.println("慢速加速！");
    33     }
    34     
    35 }


   2. Seat.java
    
     1 public interface Seat {
     2 
     3     void massage();
     4     
     5 }
     6 
     7 class LuxurySeat implements Seat {
     8     
     9     @Override
    10     public void massage() {
    11         System.out.println("按摩！");
    12     }
    13     
    14 }
    15 
    16 class LowSeat implements Seat {
    17 
    18     @Override
    19     public void massage() {
    20         System.out.println("不能按摩！");
    21     }
    22     
    23 }


   3. Tire.java
    
     1 public interface Tire {
     2 
     3     void revolve();
     4     
     5 }
     6 
     7 class LuxuryTire implements Tire {
     8 
     9     @Override
    10     public void revolve() {
    11         System.out.println("旋转不磨损！");
    12     }
    13     
    14 }
    15 
    16 class LowTire implements Tire {
    17 
    18     @Override
    19     public void revolve() {
    20         System.out.println("旋转磨损快！");
    21     }
    22     
    23 }


   4. CarFactory.java
    
    
     1 public interface CarFactory {
     2 
     3     Engine createEngine();
     4     
     5     Seat createSeat();
     6     
     7     Tire createTire();
     8     
     9 }
    10 
    11 class LuxuryCarFactory implements CarFactory {
    12     
    13     @Override
    14     public Engine createEngine() {
    15         return new LuxuryEngine();
    16     }
    17     
    18     @Override
    19     public Seat createSeat() {
    20         return new LuxurySeat();
    21     }
    22     
    23     @Override
    24     public Tire createTire() {
    25         return new LuxuryTire();
    26     }
    27     
    28 }
    29 
    30 class LowCarFactory implements CarFactory {
    31 
    32     @Override
    33     public Engine createEngine() {
    34         return new LowEngine();
    35     }
    36 
    37     @Override
    38     public Seat createSeat() {
    39         return new LowSeat();
    40     }
    41 
    42     @Override
    43     public Tire createTire() {
    44         return new LowTire();
    45     }
    46     
    47 }


   5. Client.java
    
     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         CarFactory carFactory = new LuxuryCarFactory();
     5         Engine engine = carFactory.createEngine();
     6         engine.start();
     7         engine.speedUp();
     8     }
     9 
    10 }

    
  应用场景
   1. JDK中Calendar.getInstance()方法。
    
   2. JDBC中Connection对象的获取。
    
   3. Hibernate中SessionFactory创建Session。
    
   4. Spring中IoC容器创建管理Bean对象。
    
   5. 反射中Class对象的newInstance()方法。