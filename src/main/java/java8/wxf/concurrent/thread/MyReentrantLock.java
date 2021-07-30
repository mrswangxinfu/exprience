package java8.wxf.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantLock 是 java.util.concurrent（J.U.C）包中的锁。
 *
 * 通过构造方法的boolean参数确定为公平与非公平锁, 默认是非公平锁
 *
 *
 * Synchronized 与 ReentrantLock比较：
     * 1. 锁的实现
     * synchronized 是 JVM 实现的，而 ReentrantLock 是 JDK 实现的。
     * 2. 性能
     * 新版本 Java 对 synchronized 进行了很多优化，例如自旋锁等，synchronized 与 ReentrantLock 大致相同。
     * 3. 等待可中断
     * 当持有锁的线程长期不释放锁的时候，正在等待的线程可以选择放弃等待，改为处理其他事情。
     * ReentrantLock 可中断，而 synchronized 不行。
     * 4. 公平锁
     * 公平锁是指多个线程在等待同一个锁时，必须按照申请锁的时间顺序来依次获得锁。
     * synchronized 中的锁是非公平的，ReentrantLock 默认情况下也是非公平的，但是也可以是公平的。
     * 5. 锁绑定多个条件
     * 一个 ReentrantLock 可以同时绑定多个 Condition 对象。
 *
 * 使用选择：
 * 除非需要使用 ReentrantLock 的高级功能，否则优先使用 synchronized。这是因为 synchronized 是 JVM 实现的一
 * 种锁机制，JVM 原生地支持它，而 ReentrantLock 不是所有的 JDK 版本都支持。并且使用 synchronized 不用担心没
 * 有释放锁而导致死锁问题，因为 JVM 会确保锁的释放。
 */
public class MyReentrantLock {
    private Lock lock = new ReentrantLock(false);

    public void testLock() {
        lock.lock();
        try {
            for (int i=0;i<10;i++) {
                System.out.println(i);
            }
        }finally {
            lock.unlock();
        }
        System.out.println("===");
    }

    // 显式可重入锁
    // 可重入锁的工作原理很简单，就是用一个计数器来记录锁被获取的次数，获取锁一次计数器+1，释放锁一次计数器-1，当计数器为0时，表示锁可用。
    // TODO: 实现不可重入锁
    /**
     * 1、可重入锁指的是可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
     *   与可重入锁相反，不可重入锁不可递归调用，递归调用就发生死锁。
     * 2、隐式锁（即synchronized关键字使用的锁）默认是可重入锁，显式锁（即Lock）也有ReentrantLock这样的可重入锁。
     * 3、可重入锁的工作原理很简单，就是用一个计数器来记录锁被获取的次数，获取锁一次计数器+1，释放锁一次计数器-1，当计数器为0时，表示锁可用。
     * 4、不可重入锁也叫自旋锁。
     */
    public void reEntrantLock() {
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("外层同步调用");
                try {
                    lock.lock();
                    System.out.println("内层同步调用");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }
    public static void main(String[] args) {
        MyReentrantLock myReentrantLock = new MyReentrantLock();
        MyReentrantLock myReentrantLock1 = new MyReentrantLock();
        ExecutorService executor = Executors.newCachedThreadPool();
//        executor.execute(()->{
//            myReentrantLock.testLock();
//        });
//        executor.execute(()->{
//            myReentrantLock1.testLock();
//        });
        myReentrantLock.reEntrantLock();
    }
}
