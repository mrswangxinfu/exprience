package java8.wxf.dataStructure;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class MyConcurrentHashMap {
    private String name;
    public long SIZECTL = 1;
    public void setName (String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
//        Unsafe unsafe = Unsafe.getUnsafe();
        Field size = MyConcurrentHashMap.class.getDeclaredField("SIZECTL");
        System.out.println(size);
        long s = unsafe.objectFieldOffset(size);
        String string = "hello";
        Field field = String.class.getDeclaredField("value");
        field.setAccessible(true);
        char[] value = (char[]) field.get(string);
        System.out.println(value.length);
        for (char a: value
             ) {
            System.out.println(a);
        }
    }
}
