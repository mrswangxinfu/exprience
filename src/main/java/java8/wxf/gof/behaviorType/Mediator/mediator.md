中介者模式（Mediator）

效果
1. 如果一个系统中对象之间的联系呈现为网状结构，对象之间存在大量多对多关系，将导致关系及其复杂，这些对象称为“同事对象”。

2. 引入一个中介者对象，使各同事对象只跟中介者对象打交道，将复杂的网状结构解耦成星状结构。

3. 解耦多个同事对象之间的交互关系。每个对象都持有中介者对象的引用，只跟中介者对象打交道。我们通过中介者对象统一管理这些交互关系。




代码


1. 公司有总经理，各个部门有事情都通报给总经理，总经理再通知各个相关部门。总经理起到中介、协调作用。


   2. Colleague.java
   
    1 public interface Colleague {
    2 
    3     void selfAction();
    4     
    5     void outAction();
    6     
    7 }

   3. Mediator.java

    1 public interface Mediator {
    2 
    3     void register(String name, Colleague colleague);
    4     
    5     void command(String name);
    6     
    7 }


   4. DevelopmentDepartment.java

     1 public class DevelopmentDepartment implements Colleague {
     2 
     3     private Mediator mediator;
     4     
     5     public DevelopmentDepartment(Mediator mediator) {
     6         this.mediator = mediator;
     7         mediator.register("研发部", this);
     8     }
     9 
    10     @Override
    11     public void selfAction() {
    12         System.out.println("专心研发！");
    13     }
    14 
    15     @Override
    16     public void outAction() {
    17         System.out.println("研发部向总经理汇报：需要资金支持！");
    18         mediator.command("财务部");
    19     }
    20 
    21 }


   5. MarcketDepartment.java

     1 public class MarcketDepartment implements Colleague {
     2 
     3     private Mediator mediator;
     4     
     5     public MarcketDepartment(Mediator mediator) {
     6         this.mediator = mediator;
     7         mediator.register("市场部", this);
     8     }
     9 
    10     @Override
    11     public void selfAction() {
    12         System.out.println("专心接项目");
    13     }
    14 
    15     @Override
    16     public void outAction() {
    17         System.out.println("市场部向总经理汇报：需要资金支持！");
    18     }
    19 
    20 }


   6. FinacialDepartment.java

     1 public class FinacialDepartment implements Colleague {
     2 
     3     private Mediator mediator;
     4     
     5     public FinacialDepartment(Mediator mediator) {
     6         this.mediator = mediator;
     7         mediator.register("财务部", this);
     8     }
     9 
    10     @Override
    11     public void selfAction() {
    12         System.out.println("专心数钱！");
    13     }
    14 
    15     @Override
    16     public void outAction() {
    17         System.out.println("财务部向总经理汇报：钱太多，花不完！");
    18         mediator.command("市场部");
    19     }
    20 
    21 }


   7. GeneralManager.java

     1 import java.util.HashMap;
     2 import java.util.Map;
     3 
     4 public class GeneralManager implements Mediator {
     5 
     6     private Map<String, Colleague> colleagues = new HashMap<>();
     7     
     8     @Override
     9     public void register(String name, Colleague colleague) {
    10         colleagues.put(name, colleague);
    11     }
    12 
    13     @Override
    14     public void command(String name) {
    15         colleagues.get(name).outAction();
    16     }
    17 
    18 }


   8. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         Mediator generalManager = new GeneralManager();
     5         Colleague developmentDepartment = new DevelopmentDepartment(generalManager);
     6         Colleague marcketDepartment = new MarcketDepartment(generalManager);
     7         Colleague finacialDepartment = new FinacialDepartment(generalManager);
     8         
     9         developmentDepartment.selfAction();
    10         developmentDepartment.outAction();
    11     }
    12 
    13 }


应用场景
1. MVC模式的C是中介者对象，M和V都和他打交道。

2. GUI中，多个组件之间交互，可以引入一个中介者对象（整体窗口对象或DOM对象）。

3. java.lang.reflect.Method#invoke()。