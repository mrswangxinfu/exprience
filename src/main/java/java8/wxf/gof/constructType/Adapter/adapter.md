适配器模式（Adapter）

 效果:
   
   将一个类的接口转换成客户希望的另一个接口，使原本由于接口不兼容而不能一起工作的那些类可以在一起工作。




核心角色
1. 目标接口（Target）：客户所期望的接口（接口、抽象类或具体类）。

2. 适配的类（Adaptee）。

3. 适配器（Adapter）：通过包装适配的类，把原接口转换成目标接口。


分类
1. 使用继承实现：Adapter继承Adaptee。

2. 使用关联实现：Adapter持有Adaptee的引用，Adapter可以继承其他类，更灵活。


  代码（使用继承）
   1. Target.java

    1 public interface Target {
    2 
    3     void handleRequest();
    4     
    5 }
    
   2. Adaptee.java

    1 public class Adaptee {
    2 
    3     public void request() {
    4         System.out.println("处理请求！");
    5     }
    6     
    7 }


   3. Adapter.java

    1 public class Adapter extends Adaptee implements Target {
    2     
    3     @Override
    4     public void handleRequest() {
    5         request();
    6     }
    7 
    8 }



   4. Client.java

     1 public class Client {
     2     
     3     public void execute(Target target) {
     4         target.handleRequest();
     5     }
     6 
     7     public static void main(String[] args) {
     8         Target target = new Adapter();
     9         new Client().execute(target);
    10     }
    11 
    12 }


代码（使用关联）

   1. Target.java

    1 public interface Target {
    2 
    3     void handleRequest();
    4     
    5 }
    
   2. Adaptee.java

    1 public class Adaptee {
    2 
    3     public void request() {
    4         System.out.println("处理请求！");
    5     }
    6     
    7 }


   3. Adapter.java


     1 public class Adapter implements Target {
     2     
     3     // 使用关联更灵活，这样适配器可以继承其他类
     4     private Adaptee adaptee;
     5 
     6     public Adapter(Adaptee adaptee) {
     7         this.adaptee = adaptee;
     8     }
     9 
    10     @Override
    11     public void handleRequest() {
    12         adaptee.request();
    13     }
    14 
    15 }


   4. Client.java

     1 public class Client {
     2     
     3     public void execute(Target target) {
     4         target.handleRequest();
     5     }
     6 
     7     public static void main(String[] args) {
     8         Target target = new Adapter();
     9         new Client().execute(target);
    10     }
    11 
    12 }


应用场景
1. 做旧系统改造和升级。

2. java.io.InputStreamReader(InputStream)。

3. java.io.OutputStreamWriter(OutputStream)。