package java8.wxf.concurrent.otherModule;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ava.util.concurrent.BlockingQueue 接口有以下阻塞队列的实现：
 * FIFO 队列 ：LinkedBlockingQueue、ArrayBlockingQueue（固定长度）
 * 优先级队列 ：PriorityBlockingQueue
 * 提供了阻塞的 take() 和 put() 方法：如果队列为空 take() 将阻塞，直到队列中有内容；如果队列为满 put() 将阻塞，
 * 直到队列有空闲位置。
 *
 *
 * 使用 BlockingQueue 实现生产者消费者问题
 */
public class MyBlockingQueue {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    private static class Producer extends Thread {

        @SneakyThrows
        public void run () {
            queue.put("producer");
            System.out.println("put: producer");
        }
    }
    private static class Consumer extends Thread {

        @SneakyThrows
        public void run () {
            String product = queue.take();
            System.out.println("take: " + product);
        }
    }

    public static void main(String[] args) {
        for (int i=0;i<2;i++) {
            new Producer().start();
        }

        for (int i=0;i<5;i++) {
            new Consumer().start();
        }

        for (int i=0;i<3;i++) {
            new Producer().start();
        }
    }
}
