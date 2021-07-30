package java8.wxf.gof.behaviorType.Iterator;

public interface Aggregate {
    Boolean add(Object element);
    Boolean remove(Object element);
    Iterator iterator();
    Integer size();
}
