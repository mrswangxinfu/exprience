桥接模式（Bridge）

桥接模式(Bridge Pattern)：将抽象部分与它的实现部分分离，使它们都可以独立地变化。它是一种对象结构型模式，又称为柄体(Handle and Body)模式或接口(Interface)模式。

效果
1. 处理多层继承结构、处理多维变化的场景，将各个维度设计成独立的继承结构，使各个维度可以独立的扩展在抽象层建立关联。

2. 以商场系统中的电脑分类为例，多层继承不利于扩展性（类个数膨胀问题）：

    a) 如果要增加一个新的电脑类型智能手机，则要增加各个品牌下面的类。

    b) 如果要增加一个新的品牌，也要增加各种电脑类型的类。

3. 违反单一职责原则：比如一个联想笔记本，有两个引起这个类变化的原因。



4. 桥接模式将电脑拆分为类型和品牌两个维度。




  代码

   1. Computer.java

     1 public abstract class Computer {
     2 
     3     protected Brand brand;
     4 
     5     public Computer(Brand brand) {
     6         this.brand = brand;
     7     }
     8     
     9     public abstract void run();
    10     
    11 }
    12 
    13 class Desktop extends Computer {
    14 
    15     public Desktop(Brand brand) {
    16         super(brand);
    17     }
    18 
    19     @Override
    20     public void run() {
    21         System.out.println(brand.getName() + "台式机运行！");
    22     }
    23     
    24 }
    25 
    26 class Laptop extends Computer {
    27 
    28     public Laptop(Brand brand) {
    29         super(brand);
    30     }
    31 
    32     @Override
    33     public void run() {
    34         System.out.println(brand.getName() + "笔记本运行！");
    35     }
    36     
    37 }



   2. Brand.java

     1 public interface Brand {
     2 
     3     String getName();
     4     
     5 }
     6 
     7 class Lenovo implements Brand {
     8     
     9     @Override
    10     public String getName() {
    11         return "联想";
    12     }
    13     
    14 }
    15 
    16 class Dell implements Brand {
    17 
    18     @Override
    19     public String getName() {
    20         return "戴尔";
    21     }
    22     
    23 }


  3. Client.java

    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Brand brand = new Lenovo();
    5         Computer computer = new Laptop(brand);
    6         computer.run();
    7     }
    8 
    9 }


应用场景
1. JDBC驱动程序。

2. 银行日志管理。

    a) 格式分类：操作日志、交易日志、异常日志。

    b) 距离分类：本地记录日志、异地记录日志。

3. 人力资源系统的奖金计算模块。

    a) 奖金分类：个人奖金、团体奖金、激励奖金。

    b) 部门分类：人事部门、销售部门、研发部门。

4. OA系统的消息处理。

    a) 业务类型：普通消息、加急消息、特急消息。

    b) 发送消息方式：系统内消息、手机短信、邮件。