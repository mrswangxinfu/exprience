package java8.wxf.gof.behaviorType.Command;

/**
 * 应用于回调函数
 *
 * 例子：拿订餐来说，客人需要向厨师发送请求，但是完全不知道这些厨师的名字和联系方式，
 * 也不知道厨师炒菜的方式和步骤。 命令模式把客人订餐的请求封装成 command 对象，
 * 也就是订餐中的订单对象。这个对象可以在程序中被四处传递，就像订单可以从服务员
 * 手中传到厨师的手中。这样一来，客人不需要知道厨师的名字，从而解开了请求调用者和请求接收者之间的耦合关系
 *
 * 定义：将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
 *      这样两者之间通过命令对象进行沟通，这样方便将命令对象进行储存、传递、调用、增加与管理。
 * 命令模式的实现过程通过调用者调用接受者执行命令
 */
public class Client {
    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command cmd = new ConcreteCommand("cmd",receiver);
        Command mysql = new ConcreteCommand("mysql",receiver);
        Command jvm = new ConcreteCommand("jvm",receiver);
        Invoker invoker = new Invoker("BigInvoker", cmd, mysql, jvm, new Command() {
            @Override
            public void execute(String invokerName) {
                System.out.println("调用者是"+invokerName);
                receiver.Action(invokerName,new ConcreteCommand("shell",receiver));
            }
        });// TODO:使用匿名内部类直接实现接口
        invoker.call();
        System.out.println("===================");
        invoker.call();
//        new Command() {
//            @Override
//            public void execute(String invokerName) {
//                System.out.println("调用者是===="+invokerName);
//                receiver.Action(invokerName,new ConcreteCommand("shell",receiver));
//            }
//            public void say() {
//                System.out.println("增加的方法"+"say");
//            }
//        }.execute("uuuu");
//
//        new Command() {
//            @Override
//            public void execute(String invokerName) {
//                System.out.println("调用者是"+invokerName);
//                receiver.Action(invokerName,new ConcreteCommand("--匿名say--",receiver));
//            }
//            public Command say() {
//                System.out.println("增加的方法"+"say,返回值是this");
//                return this;
//            }
//        }.say().execute("NewInvoker");
    }
}
