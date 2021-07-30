package java8.wxf.gof.behaviorType.Command;

/**
 * 抽象命令
 */
public interface Command {
    // TODO：可设计多个方法
    void execute(String invokerName);
}
