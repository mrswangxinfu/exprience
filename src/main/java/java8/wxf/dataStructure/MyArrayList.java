package java8.wxf.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ArrayList扩容方式：先判断ArrayList是否是通过无参构造器实例化，是则比较默认容量（10）与size+1的大小，
 * 再判断当前数组size+1(size是元素的个数)后是否大于数组长度，是就扩容0.5倍，
 * 再判断扩容0.5倍后是否大于size+1，若小于则扩容为size+1,
 */
public class MyArrayList {
    transient Full hello;
    boolean override;
    public MyArrayList () {

    }
    private abstract class Full<T> {
       abstract public <U> T get();
       public void next () {
          get();
       }

    }
    public static  <T,P> void get(T obj, P u) {

        System.out.println(obj.getClass()+""+ u.getClass());
    }
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(12);
        arrayList.add(30);
        Object[] test={1,2,3};
        arrayList.toArray(test);
        for (Object o : test) {
            System.out.println("test:"+o);
        }

        System.out.println(arrayList.size());
        arrayList.add(15);
        System.out.println(arrayList.size());
        List<Integer> list = new ArrayList<>(arrayList);
        System.out.println(list);
//        MyArrayList.get(12,"===");
        String name = MyLinkedList.Full.name;
    }
}
