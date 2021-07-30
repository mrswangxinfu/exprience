package java8.wxf.gof.behaviorType.Observer.BaseJDK;

import java.util.Observer;

public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver("张三");
        Observer observer2 = new ConcreteObserver("李四");
        Observer observer3 = new ConcreteObserver("王五");
        subject.addObserver(observer1);
        subject.addObserver(observer2);
        subject.addObserver(observer3);

        subject.setState(1);
        subject.setState(2);
    }
}
