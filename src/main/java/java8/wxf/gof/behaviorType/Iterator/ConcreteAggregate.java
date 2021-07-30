package java8.wxf.gof.behaviorType.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 模仿集合类
 */
public class ConcreteAggregate implements Aggregate {
    private List<Object> list = new ArrayList<>();
    @Override
    public Boolean add(Object element) {
        return list.add(element);
    }

    @Override
    public Boolean remove(Object element) {
        return list.remove(element);
    }

    @Override
    public Iterator iterator() {
        return new ConcreteAggregateIterator();
    }
    public Integer size() {
        return list.size();
    }
    private class ConcreteAggregateIterator implements Iterator {
        int cursor;
        @Override
        public Boolean hasNext() {
            // 使用ConcreteAggregate.this区分内部类的this
            return cursor < ConcreteAggregate.this.list.size();
        }

        @Override
        public Object next() {
            cursor++;
            return list.get(cursor);
        }
    }
}
