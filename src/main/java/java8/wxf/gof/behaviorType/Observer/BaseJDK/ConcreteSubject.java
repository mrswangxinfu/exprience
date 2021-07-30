package java8.wxf.gof.behaviorType.Observer.BaseJDK;

import java.util.Observable;

public class ConcreteSubject extends Observable {
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        // 目标对象已变化
        setChanged();
        // 通知观察者
        notifyObservers(state);
    }
}
