package java8.wxf.concurrent.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。
 *
 * 从概念上将，Semaphore包含一组许可证。
 * 如果有需要的话，每个acquire()方法都会阻塞，直到获取一个可用的许可证。
 * 每个release()方法都会释放持有许可证的线程，并且归还Semaphore一个可用的许可证。
 * 然而，实际上并没有真实的许可证对象供线程使用，Semaphore只是对可用的数量进行管理维护
 */
public class MySemaphore {
    public static void main(String[] args) {
        final int clientTotal = 3;
        final int requestTotal = 10;
        ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(clientTotal);

        for (int i=0;i<requestTotal;i++) {
            executor.execute(() -> {
                try {
                    System.out.println(semaphore.availablePermits()+"===");
                    // 阻塞
                    semaphore.acquire();
                    System.out.println(semaphore.availablePermits()+"--");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放
                    semaphore.release();
                }
            });
        }
        executor.shutdown();
    }
}
