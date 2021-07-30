package java8.wxf.concurrent.JMM;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 线程本地存储（Thread Local Storage）
 * 如果一段代码中所需要的数据必须与其他代码共享，那就看看这些共享数据的代码是否能保证在同一个线程中执行。
 * 如果能保证，我们就可以把共享数据的可见范围限制在同一个线程之内，这样，无须同步也能保证线程之间不出现数
 * 据争用的问题。
 * 符合这种特点的应用并不少见，大部分使用消费队列的架构模式（如“生产者-消费者”模式）都会将产品的消费过程尽
 * 量在一个线程中消费完。其中最重要的一个应用实例就是经典 Web 交互模型中的“一个请求对应一个服务器线
 * 程”（Thread-per-Request）的处理方式，这种处理方式的广泛应用使得很多 Web 服务端应用都可以使用线程本地
 * 存储来解决线程安全问题。
 * 可以使用 java.lang.ThreadLocal 类来实现线程本地存储功能。
 *
 * thread1 中设置 threadLocal 为 1，而 thread2 设置 threadLocal 为 2。过了一段时间之后，
 * thread1 读取 threadLocal 依然是 1，不受 thread2 的影响。
 *
 *
 *  每个 Thread 都有一个 ThreadLocal.ThreadLocalMap 对象。
 *  当调用一个 ThreadLocal 的 set(T value) 方法时，先得到当前线程的 ThreadLocalMap 对象，然后将 ThreadLocal-
 *  >value 键值对插入到该 Map 中。
 */
public class MyThreadLocal {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
//        AtomicStampedReference stampedReference = new AtomicStampedReference(10,2);
//        System.out.println(stampedReference.getReference());
//        System.out.println(stampedReference.getStamp());
//        stampedReference.set(2,3);
//        System.out.println(stampedReference.getReference());
//        System.out.println(stampedReference.getStamp());
//        stampedReference.compareAndSet(3,5,2,5);
//        System.out.println(stampedReference.getReference());
//        System.out.println(stampedReference.getStamp());
//        stampedReference.compareAndSet(2,20,3,21);
//        System.out.println(stampedReference.getReference());
//        System.out.println(stampedReference.getStamp());
        new Thread(() -> {
            threadLocal1.set(5);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal1.get());
            threadLocal1.remove();
        }).start();
        new Thread(() -> {
            threadLocal1.set(6);
            System.out.println(threadLocal1.get());
            threadLocal1.remove();
        }).start();
        System.out.println(threadLocal1);
    }
}
