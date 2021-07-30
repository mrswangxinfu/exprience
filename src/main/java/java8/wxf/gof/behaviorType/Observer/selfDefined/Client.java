package java8.wxf.gof.behaviorType.Observer.selfDefined;

public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver("张三");
        Observer observer2 = new ConcreteObserver("李四");
        Observer observer3 = new ConcreteObserver("王五");
        // 添加观察者
        subject.subscribe(observer1);
        subject.subscribe(observer2);
        subject.subscribe(observer3);

        subject.setState(1);
        System.out.println("============");
        subject.setState(2);
    }
}
