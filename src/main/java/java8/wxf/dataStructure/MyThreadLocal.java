package java8.wxf.dataStructure;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal 为每个线程创建一个ThreadLocalMap实例，
 * 每个ThreadLocalMap实例使用每个ThreadLocal实例作为键，保存每个线程需要保存的值
 */
public class MyThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(45);
        System.out.println(threadLocal.get());
        threadLocal.set(100);
        System.out.println(threadLocal.get());
        new Thread(){
            @Override
            public void run() {
//                Thread.currentThread().setName("thread==");
                threadLocal.set(12);
                System.out.println(Thread.currentThread().getName()+threadLocal.get());
            }
        }.start();

        System.out.println(threadLocal.get());
    }
}
