模板方法模式（Template Method）

效果
1. 常用的模式。

2. 模板方法定义一个操作的算法框架，将某些步骤延迟到子类中实现。这样，新的子类可以在不改变算法结构的前提下，重新定义该算法的某些特定步骤。


代码
   1. BankTemplateMethod.java

     1 public abstract class BankTemplateMethod {
     2     
     3     protected void takeNumber() {
     4         System.out.println("取号！");
     5     }
     6 
     7     protected void waitInLine() {
     8         System.out.println("排队！");
     9     }
    10     
    11     // 钩子方法/回调方法：办理具体业务
    12     protected abstract void transaction();
    13     
    14     protected void evaluate() {
    15         System.out.println("评分！");
    16     }
    17     
    18     // 模板方法
    19     public final void process() {
    20         takeNumber();
    21         waitInLine();
    22         transaction();
    23         evaluate();
    24     }
    25     
    26 }


   2. DrawMoney.java

    1 public class DrawMoney extends BankTemplateMethod {
    2 
    3     @Override
    4     protected void transaction() {
    5         System.out.println("取款！");
    6     }
    7 
    8 }

   3. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         BankTemplateMethod drawMoney = new DrawMoney();
     5         drawMoney.process();
     6         
     7         // 匿名内部类实现
     8         new BankTemplateMethod() {
     9 
    10             @Override
    11             protected void transaction() {
    12                 System.out.println("存款！");
    13             }
    14             
    15         }.process();;
    16     }
    17 
    18 }

应用场景
1. 各框架、类库都有模板方法。

2. 数据库访问的封装。

3. JUnit单元测试。

4. Servlet的doGet()和doPost()方法调用。

5. Spring的JDBCTemplate、HibernateTemplate。