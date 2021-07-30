package java8.wxf.gof.behaviorType.Command;

public class ConcreteCommand implements Command{
    private Receiver receiver;
    private String name;
    ConcreteCommand(String name, Receiver receiver) {
        this.receiver = receiver;
        this.name = name;
    }
    @Override
    public void execute(String invokerName) {
        // TODO: 可在执行前后做其他操作，比如记录日志
        receiver.Action(invokerName,this);
    }
    public String getName() {
        return name;
    }
}
