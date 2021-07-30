package java8.wxf.concurrent.AQS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier：
 *     CyclicBarrier内部使用了ReentrantLock和Condition两个类。
 *     调用await方法的线程告诉CyclicBarrier自己已经到达同步点，然后当前线程被阻塞。
 *     直到parties（构造方法参数）个参与线程调用了await方法
 * 用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。
 * 和 CountdownLatch 相似，都是通过维护计数器来实现的。线程执行 await() 方法之后计数器会减 1，并进行等待，
 * 直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行。
 * CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以
 * 它才叫做循环屏障。
 * CyclicBarrier 有两个构造函数，其中 parties 指示计数器的初始值，barrierAction 在所有线程都到达屏障的时候会
 * 执行一次。
 */
public class MyCyclicBarrier {

    public static void main(String[] args) {
        int totalThread = 10;
        ExecutorService executor = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10,()->{
            System.out.println("时间到，结束。。。");
        });
        for (int i=0;i<totalThread;i++) {
            executor.execute(() -> {
                System.out.println("before");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("after");
            });
        }
//        executor.shutdownNow();
        executor.shutdown();
    }
}
