package java8.wxf.dataStructure;

import sun.reflect.CallerSensitive;

import java.util.LinkedList;
import java.util.List;

/**
 * LinkedList底层是以Node节点存储数据的双向链表
 */
public class MyLinkedList extends MyArrayList{

    public static class Full {
       public static String name;
       public void setName (String name) {
           this.name = name;
       }
       public String getName () {
           return this.name;
       }
//       abstract void getResource();
    }
    public void test () {
        if (override) {
            System.out.println(new LinkedList<>());
        }
    }
    public static void main(String[] args) {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        System.out.println(classLoader);
//        System.out.println(classLoader.getParent());
//        System.out.println(classLoader.getParent().getParent());
    }
}
