构建者模式（Builder）
（一个产品有多个子组件（字段属性），通过构建子组件，然后装配子组件到产品中，即完成生产产品）
效果
1. 构建一个复杂的产品（如神舟飞船、iPhone）时，需要解决“如何装配子组件”的问题。

2. 分离了对象子组件的单独构建（Builder）和装配（Director），从而构造出复杂对象。

3. 由于实现了构建和装配的解耦。不同的构建器、相同的装配，或者相同的构建器、不同的装配，都可以创建不同的对象。

4. 建造者模式一般与工厂模式搭配，由工厂模式创建子组件，再有建造者模式装配。

代码

   1. Airship.java


     1 public class Airship {
     2 
     3     private Engine engine;
     4     
     5     private OrbitalModule orbitalModule;
     6     
     7     private EscapeTower escapeTower;
     8     
     9     public void launch() {
    10         System.out.println(engine.getName() + "点火！");
    11         System.out.println(orbitalModule.getName() + "发射！");
    12     }
    13 
    14     public Engine getEngine() {
    15         return engine;
    16     }
    17 
    18     public void setEngine(Engine engine) {
    19         this.engine = engine;
    20     }
    21 
    22     public OrbitalModule getOrbitalModule() {
    23         return orbitalModule;
    24     }
    25 
    26     public void setOrbitalModule(OrbitalModule orbitalModule) {
    27         this.orbitalModule = orbitalModule;
    28     }
    29 
    30     public EscapeTower getEscapeTower() {
    31         return escapeTower;
    32     }
    33 
    34     public void setEscapeTower(EscapeTower escapeTower) {
    35         this.escapeTower = escapeTower;
    36     }
    37     
    38 }
    39 
    40 class Engine {
    41     
    42     private String name;
    43     
    44     public String getName() {
    45         return name;
    46     }
    47     
    48     public void setName(String name) {
    49         this.name = name;
    50     }
    51     
    52 }
    53 
    54 class OrbitalModule {
    55     
    56     private String name;
    57     
    58     public String getName() {
    59         return name;
    60     }
    61     
    62     public void setName(String name) {
    63         this.name = name;
    64     }
    65     
    66 }
    67 
    68 class EscapeTower {
    69     
    70     private String name;
    71 
    72     public String getName() {
    73         return name;
    74     }
    75 
    76     public void setName(String name) {
    77         this.name = name;
    78     }
    79     
    80 }


   2. AirshipBuilder.java

    1 public interface AirshipBuilder {
    2 
    3     Engine buildEngine();
    4     
    5     OrbitalModule buildOrbitalModule();
    6     
    7     EscapeTower buildEscapeTower();
    8     
    9 }


   3. AirshipDirector.java

    1 public interface AirshipDirector {
    2 
    3     Airship directAirship();
    4     
    5 }


   4. SzAirshipBuilder.java

     1 public class SzAirshipBuilder implements AirshipBuilder {
     2 
     3     @Override
     4     public Engine buildEngine() {
     5         // 也可使用工厂模式创建
     6         Engine engine = new Engine();
     7         engine.setName("神舟发动机");
     8         return engine;
     9     }
    10 
    11     @Override
    12     public OrbitalModule buildOrbitalModule() {
    13         // 也可使用工厂模式创建
    14         OrbitalModule orbitalModule = new OrbitalModule();
    15         orbitalModule.setName("神舟轨道舱");
    16         return orbitalModule;
    17     }
    18 
    19     @Override
    20     public EscapeTower buildEscapeTower() {
    21         // 也可使用工厂模式创建
    22         EscapeTower escapeTower = new EscapeTower();
    23         escapeTower.setName("神舟逃逸塔");
    24         return escapeTower;
    25     }
    26 
    27 }


   5. SzAirshipDirector.java

     1 public class SzAirshipDirector implements AirshipDirector {
     2 
     3     private AirshipBuilder airshipBuilder;
     4     
     5     public SzAirshipDirector(AirshipBuilder airshipBuilder) {
     6         this.airshipBuilder = airshipBuilder;
     7     }
     8 
     9     @Override
    10     public Airship directAirship() {
    11         Engine engine = airshipBuilder.buildEngine();
    12         OrbitalModule orbitalModule = airshipBuilder.buildOrbitalModule();
    13         EscapeTower escapeTower = airshipBuilder.buildEscapeTower();
    14         
    15         Airship airship = new Airship();
    16         airship.setEngine(engine);
    17         airship.setOrbitalModule(orbitalModule);
    18         airship.setEscapeTower(escapeTower);
    19         
    20         return airship;
    21     }
    22 
    23 }


  6. Client.java
  
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         AirshipDirector airshipDirector = new SzAirshipDirector(new SzAirshipBuilder());
    5         Airship airship = airshipDirector.directAirship();
    6         airship.launch();
    7     }
    8     
    9 }


应用场景
1. StringBuilder.append()方法。

2. SQL中的PareparedStatement。