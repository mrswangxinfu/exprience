享元模式（FlyWeight）

 说白了就是将对象装到集合中管理然后重用对象。例如：围棋只有黑白子，
 所以可以只装黑子白子两个对象然后不断的重用就ok啦！

效果
1. 享元模式以共享的方式高效地支持大量细粒度对象的重用。

    a) 内存属于稀缺资源，不要随便浪费。如果有很多个完全相同或相似的对象，我们可以通过享元模式节省内存。

    b) 用时间换取空间。

2. 享元对象能做到共享的关键是区分了内部状态和外部状态。

    a) 内部状态：可以共享，不会随环境变化而变化。

    b) 外部状态：不可以共享，会随环境变化而变化。




核心角色
1. 享元工厂类（FlyWeightFactory）：创建并管理享元对象，享元池一般设计成键值对。

2. 抽象享元类（FlyWeight）：通常是一个接口或抽象类，声明公共方法向外界提供对象的内部状态、设置外部状态。

3. 具体享元类（ConcreteFlyWeight）：为内部状态提供成员变量进行存储。

4. 非共享享元类（UnsharedConcreteFlyWeight）：不能被共享的子类可以设计为非共享享元类。


代码

   1. ChessFlyWeight.java

    1 // 抽象享元类
    2 public interface ChessFlyWeight {
    3 
    4     String getColor();
    5     
    6     void display(Position position);
    7     
    8 }


   2. Position.java

     1 // 非共享享元类
     2 public class Position {
     3 
     4     private int x;
     5     
     6     private int y;
     7 
     8     public Position(int x, int y) {
     9         this.x = x;
    10         this.y = y;
    11     }
    12 
    13     public int getX() {
    14         return x;
    15     }
    16 
    17     public int getY() {
    18         return y;
    19     }
    20 
    21 }


   3. ConcreteChess.java

     1 // 具体享元类
     2 public class ConcreteChess implements ChessFlyWeight {
     3     
     4     private String color;
     5 
     6     public ConcreteChess(String color) {
     7         this.color = color;
     8     }
     9     
    10     @Override
    11     public String getColor() {
    12         return color;
    13     }
    14 
    15     @Override
    16     public void display(Position position) {
    17         System.out.println("显示：颜色" + color + "，位置(" + position.getX() + "," + position.getY() + ")！");
    18     }
    19 
    20 }


   4. ChessFactory.java

     1 import java.util.HashMap;
     2 import java.util.Map;
     3 
     4 // 享元工厂类
     5 public class ChessFactory {
     6 
     7     private static Map<String, ChessFlyWeight> chesses = new HashMap<>();
     8     
     9     public static ChessFlyWeight getChess(String color) {
    10         ChessFlyWeight chess = chesses.get(color);
    11         if (chess != null) {
    12             return chess;
    13         }
    14         chess = new ConcreteChess(color);
    15         chesses.put(color, chess);
    16         return chess;
    17     }
    18     
    19 }

   5. Client.java


     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         ChessFlyWeight chess1 = ChessFactory.getChess("黑");
     5         ChessFlyWeight chess2 = ChessFactory.getChess("黑");
     6         System.out.println(chess1 == chess2);
     7         
     8         chess1.display(new Position(10, 20));
     9         chess1.display(new Position(20, 10));
    10     }
    11 
    12 }


应用场景
1. 享元模式由于共享的特性，可以应用与任何“池”，比如线程池、数据库连接池。

2. String类的设计。