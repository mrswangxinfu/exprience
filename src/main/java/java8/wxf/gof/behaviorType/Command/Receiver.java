package java8.wxf.gof.behaviorType.Command;

public class Receiver {
    public void Action(String invokerName, Command command) {
        System.out.println("调用者"+invokerName+ "使" + ((ConcreteCommand)command).getName() + "命令执行");
    }
}
