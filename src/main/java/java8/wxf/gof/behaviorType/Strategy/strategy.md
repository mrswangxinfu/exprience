策略模式（Strategy）

效果
1. 策略模式对应于解决某一个问题的一个算法族，允许客户端从该算法族中任选一个算法解决问题，同时可以方便更换算法或添加新的算法。

2. 本质：分离算法，选择实现。


代码
1. 某个市场人员接到单后的报销策略（CRM系统的常见问题）。报价策略很复杂：

    a) 普通客户小批量报价；

    b) 普通客户大批量报价；

    c) 老客户小批量报价；

    d) 老客户大批量报价。



   2. Strategy.java

    1 public interface Strategy {
    2 
    3     double getPrice(double standardPrice);
    4     
    5 }

   3. Context.java

     1 // 负责与具体的策略类交互，将客户端与算法分离
     2 public class Context {
     3 
     4     private Strategy strategy;
     5 
     6     public Context(Strategy strategy) {
     7         this.strategy = strategy;
     8     }
     9     
    10     public void printPrice(double standardPrice) {
    11         System.out.println(strategy.getPrice(standardPrice));
    12     }
    13     
    14 }


  4. NewCustomerFewStrategy.java

    1 public class NewCustomerFewStrategy implements Strategy {
    2 
    3     @Override
    4     public double getPrice(double standardPrice) {
    5         System.out.println("不打折！");
    6         return standardPrice;
    7     }
    8 
    9 }


  5. NewCustomerManyStrategy.java

    1 public class NewCustomerManyStrategy implements Strategy {
    2 
    3     @Override
    4     public double getPrice(double standardPrice) {
    5         System.out.println("打九折！");
    6         return standardPrice * 0.9;
    7     }
    8 
    9 }



  6. OldCustomerFewStrategy.java

    1 public class OldCustomerFewStrategy implements Strategy {
    2 
    3     @Override
    4     public double getPrice(double standardPrice) {
    5         System.out.println("打八五折！");
    6         return standardPrice * 0.85;
    7     }
    8 
    9 }



  7. OldCustomerManyStrategy.java

    1 public class OldCustomerManyStrategy implements Strategy {
    2 
    3     @Override
    4     public double getPrice(double standardPrice) {
    5         System.out.println("打八折！");
    6         return standardPrice * 0.8;
    7     }
    8 
    9 }


  8. Client.java

    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Strategy strategy = new OldCustomerManyStrategy();    // 可通过配置生成
    5         Context context = new Context(strategy);
    6         context.printPrice(998);
    7     }
    8 
    9 }


应用场景
1. Java的GUI编程，布局管理。

2. Spring框架的Resource接口，资源访问策略。

3. javax.servlet.http.HttpServlet#service()。