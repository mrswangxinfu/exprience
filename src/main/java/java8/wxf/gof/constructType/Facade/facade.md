外观模式（Facade）

概念：为子系统提供统一的入口（外观类）

效果
1. 为子系统提供统一的入口。封装子系统的复杂性，便于客户端调用。

2. 迪米特法则：一个软件实体应当尽可能少地与其他实体发生相互作用。




代码


   1. Tea.java

    1 public interface Tea {
    2 
    3     String getName();
    4     
    5     void drink();
    6     
    7 }

   2. WaitressFacade.java


      1 public class WaitressFacade {
      2 
      3     public Tea makeTea(String teaName) {
      4         Water water = new Water("农夫山泉", 100.0);
      5         Tea tea = null;
      6         switch (teaName) {
      7         case "西湖龙井":
      8             tea = new XiHuLongJing();
      9             break;
     10         case "碧螺春":
     11             tea = new BiLuoChun();
     12             break;
     13         case "铁观音":
     14             tea = new TieGuanYin();
     15             break;
     16         default:
     17             return null;
     18         }
     19         TeaSet teaSet = new TeaSet();
     20         teaSet.cleanTeaSet(water);
     21         teaSet.addTea(tea);
     22         teaSet.cleanTea(water);
     23         teaSet.makeTea(water);
     24         return tea;
     25     }
     26     
     27 }
     28 
     29 class Water {
     30     
     31     private String name;
     32     
     33     private Double temperature;
     34 
     35     public Water(String name, Double temperature) {
     36         this.name = name;
     37         this.temperature = temperature;
     38     }
     39 
     40     public String getName() {
     41         return name;
     42     }
     43 
     44     public Double getTemperature() {
     45         return temperature;
     46     }
     47 
     48 }
     49 
     50 class TeaSet {
     51     
     52     private Tea tea;
     53     
     54     public void cleanTeaSet(Water water) {
     55         System.out.println("使用" + water.getTemperature() + "°的" + water.getName() + "烫洗茶具！");
     56     }
     57     
     58     public void addTea(Tea tea) {
     59         System.out.println("投入" + tea.getName() + "！");
     60         this.tea = tea;
     61     }
     62     
     63     public void cleanTea(Water water) {
     64         System.out.println("使用" + water.getTemperature() + "°的" + water.getName() + "洗茶！");
     65     }
     66     
     67     public Tea makeTea(Water water) {
     68         System.out.println("使用" + water.getTemperature() + "°的" + water.getName() + "泡茶！");
     69         return tea;
     70     }
     71     
     72 }
     73 
     74 class XiHuLongJing implements Tea {
     75     
     76     @Override
     77     public String getName() {
     78         return "西湖龙井";
     79     }
     80 
     81     @Override
     82     public void drink() {
     83         System.out.println("品" + getName() + "！");
     84     }
     85     
     86 }
     87 
     88 class BiLuoChun implements Tea {
     89     
     90     @Override
     91     public String getName() {
     92         return "洞庭碧螺春";
     93     }
     94     
     95     @Override
     96     public void drink() {
     97         System.out.println("品" + getName() + "！");
     98     }
     99     
    100 }
    101 
    102 class TieGuanYin implements Tea {
    103 
    104     @Override
    105     public String getName() {
    106         return "安溪铁观音";
    107     }
    108     
    109     @Override
    110     public void drink() {
    111         System.out.println("品" + getName() + "！");
    112     }
    113     
    114 }


   3. Client.java

    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         WaitressFacade waitressFacade = new WaitressFacade();
    5         Tea tea = waitressFacade.makeTea("铁观音");
    6         tea.drink();
    7     }
    8 
    9 }


应用场景
1. 频率很高，处处用到。

2. JDBC封装后的DbUtils类。

3. Hibernate提供的工具类。

4. Spring JDBC工具类等。