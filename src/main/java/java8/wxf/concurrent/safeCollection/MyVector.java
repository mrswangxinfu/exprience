package java8.wxf.concurrent.safeCollection;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Vector是一个安全集合
 */
public class MyVector {
    public static void main(String[] args) {
//        Vector<String> vector = new Vector<>();
//        vector.add("hello");
//        vector.add("");
//        System.out.println(vector.get(1));
        List<Integer> list = new ArrayList<>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0; i<50; i++) {
            executor.execute(() -> {
                list.add(new Random().nextInt(30));
            });
        }
        list.forEach(e -> {
            System.out.println(e);
        });


        for (int i=0; i<10; i++) {
            new Thread(() -> {
                list.add(new Random().nextInt(20));
            }).start();
        }
        // 其他线程在添加数据,主线程在使用foreach遍历会出现并发修改异常
        list.forEach(System.out::println);
        executor.shutdown();
//        for (int i=0; i<list.size(); i++) {
//            System.out.println("=="+list.get(i));
//        }

//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        for (int i:list) {
//            if (i==3) {
//                list.remove(1);
//            }
//            System.out.println(i);
//        }
    }
}
