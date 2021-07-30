package java8.wxf.gof.behaviorType.Iterator;

public class Client {
    public static void main(String[] args) {
        Aggregate concreteAggregate = new ConcreteAggregate();
        concreteAggregate.add(12);
        concreteAggregate.add(15);
        concreteAggregate.add(45);
        Iterator iterator = concreteAggregate.iterator();
        for (int i=0;i<concreteAggregate.size();i++) {
            System.out.println(iterator.next());
            System.out.println( "还有？" + iterator.hasNext());
        }
    }
}
