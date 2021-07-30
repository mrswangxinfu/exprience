package java8.wxf.gof.behaviorType.Observer.selfDefined;

public class ConcreteObserver implements Observer {

    private String name;
    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        System.out.println(name + "收到消息：state=" + ((ConcreteSubject) subject).getState());
    }
}
