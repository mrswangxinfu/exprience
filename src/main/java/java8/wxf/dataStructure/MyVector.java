package java8.wxf.dataStructure;

import java.util.List;
import java.util.Vector;

/**
 * Vector与ArrayList相似，底层都是Object数组，
 * 差别：1、多一个带有构造器Vector(int initialCapacity, int capacityIncrement)
 *      其中capacityIncrement是扩容的增量，若使用该构造器，则按照设置的增量进行扩容，否则2倍扩容。
 *      2、操作的方法都加synchronized修饰
 */
public class MyVector {
    public static void main(String[] args) {
        List<String> vector = new Vector<>();
    }
}
