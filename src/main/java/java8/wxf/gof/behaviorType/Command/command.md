命令模式（Command）

效果
1. 又称“动作模式（Action）、事务模式（Transaction）。

2. 将一个请求封装成一个对象，从而使我们可用不同的请求对客户进行参数化；对请求排队或者记录请求日志，以及支持可撤销的操作。




核心角色
1. 抽象命令类（Command）。

2. 具体命令类（ConcreteCommand）。

3. 调用者/请求者（Invoker）：请求的发送者，通过命令对象来执行请求。调用者并不需要在设计时确定接收者，而是在运行时，调用命令对象的execute()，间接调用接

收者的相关操作。

4. 接收者（Receiver）：执行与请求相关的操作，具体实现对请求的业务处理。


代码

   1. Command.java

    1 public interface Command {
    2 
    3     // 实际中可设计多个方法
    4     void execute();
    5     
    6 }


   2. ConcreteCommand.java

     1 public class ConcreteCommand implements Command {
     2 
     3     private Receiver receiver;
     4     
     5     public ConcreteCommand(Receiver receiver) {
     6         this.receiver = receiver;
     7     }
     8 
     9     @Override
    10     public void execute() {
    11         // 可在执行前后做其他操作，比如记录日志
    12         receiver.action();
    13     }
    14     
    15 }

   3. Invoker.java

     1 public class Invoker {
     2 
     3     // 也可以是多条命令，类似数据库事务中的多条命令
     4     private Command command;
     5 
     6     public Invoker(Command command) {
     7         this.command = command;
     8     }
     9     
    10     public void call() {
    11         command.execute();
    12     }
    13     
    14 }


   4. Receiver.java

    1 public class Receiver {
    2 
    3     public void action() {
    4         System.out.println("Receiver.action()");
    5     }
    6     
    7 }

   5. Client.java

    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Command command = new ConcreteCommand(new Receiver());
    5         Invoker invoker = new Invoker(command);
    6         invoker.call();
    7     }
    8 
    9 }


应用场景
数据库事务机制的底层实现。