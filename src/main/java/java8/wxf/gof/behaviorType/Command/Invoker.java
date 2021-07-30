package java8.wxf.gof.behaviorType.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Invoker {
    private String name;
    private List<Command> commands = new ArrayList<>();
    Invoker(String name, Command... commands) {
        this.name = name;
        this.commands.addAll(Arrays.asList(commands));
    }
    void call() {
        System.out.println("调用者："+name+"....正在处理");
        commands.forEach(e -> e.execute(name));
    }
}
