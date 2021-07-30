package java8.wxf.gof.behaviorType.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责保存好备忘录
 */
public class CareTaker {
    List<Memento> mementos = new ArrayList<>();
    public void addMemento(Memento memento) {
        mementos.add(memento);
    }
    public Memento getMemento(int index) {
        return mementos.get(index);
    }
    public Memento getMementoLast() {
        return mementos.get(mementos.size()-1);
    }
}
