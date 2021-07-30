package java8.wxf.gof.behaviorType.Observer.BaseJDK;

import java.util.Observable;
import java.util.Observer;

public class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }
    @Override
    public void update(Observable o, Object arg) {
        ConcreteSubject subject = (ConcreteSubject) o;
        System.out.println(name + "收到消息：" + arg);
        System.out.println(name + "获取最新状态：" + subject.getState());
    }
}
