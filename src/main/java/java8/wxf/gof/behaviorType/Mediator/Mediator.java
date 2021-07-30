package java8.wxf.gof.behaviorType.Mediator;

public interface Mediator {
    void registry(String name, Colleague colleague);
    void comment(String name);
}
