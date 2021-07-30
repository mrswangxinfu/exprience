迭代器模式（Iterator）

效果
1. 又称游标模式（Cursor）。

2. 提供一种可以遍历聚合对象的方式。


核心角色
1. 聚合对象（Aggregate）：存储数据。

2. 迭代器（Iterator）：遍历数据的算法，比如正序、倒序、随机等。


代码

   1. Iterator.java

    1 public interface Iterator {
    2 
    3     boolean hasNext();
    4     
    5     Object next();
    6     
    7 }


   2. Aggregate.java

    1 public interface Aggregate {
    2 
    3     void add(Object element);
    4     
    5     void remove(Object element);
    6     
    7     Iterator iterator();
    8     
    9 }


   3. ConcreteAggregate.java

     1 import java.util.ArrayList;
     2 import java.util.List;
     3 
     4 public class ConcreteAggregate implements Aggregate {
     5 
     6     private List<Object> list = new ArrayList<>();
     7     
     8     @Override
     9     public void add(Object element) {
    10         list.add(element);
    11     }
    12 
    13     @Override
    14     public void remove(Object element) {
    15         list.remove(element);
    16     }
    17 
    18     @Override
    19     public Iterator iterator() {
    20         return new ConcreteAggregateIterator();
    21     }
    22     
    23     // 定义成内部类，可直接访问外部类的属性
    24     private class ConcreteAggregateIterator implements Iterator {
    25 
    26         private int cursor = -1;
    27         
    28         @Override
    29         public boolean hasNext() {
    30             return cursor < list.size() - 1;
    31         }
    32 
    33         @Override
    34         public Object next() {
    35             cursor++;
    36             return list.get(cursor);
    37         }
    38 
    39     }
    40 
    41 }


   4. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         Aggregate aggregate = new ConcreteAggregate();
     5         aggregate.add("A");
     6         aggregate.add("B");
     7         aggregate.add("C");
     8         aggregate.add("D");
     9         aggregate.add("E");
    10         
    11         Iterator iterator = aggregate.iterator();
    12         while (iterator.hasNext()) {
    13             System.out.println(iterator.next());
    14         }
    15     }
    16 
    17 }


应用场景

  JDK内置的迭代器