package java8.wxf.concurrent.AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch: 有时间限制而倒计时为0时自动解锁的锁
 * 用来控制一个线程等待多个线程。
 * 维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方
 * 法而在等待的线程就会被唤醒。
 */
public class MyCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i=0;i<totalThread;i++) {
            int finalI = i;
            executor.execute(() -> {
                // 开始倒计时
                countDownLatch.countDown();
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("run"+ finalI);
            });
        }
        // 等待上面所有线程执行完成
        countDownLatch.await();
        System.out.println("end");
//        executor.shutdownNow();
        executor.shutdown();
    }
}
