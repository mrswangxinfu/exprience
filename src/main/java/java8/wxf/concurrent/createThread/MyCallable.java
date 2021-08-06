package java8.wxf.concurrent.createThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 通过Callable创建线程
 * 与 Runnable 相比，Callable 可以有返回值，返回值通过 FutureTask 进行封装。
 *
 * 多个线程调用同一futureTask只进去futureTask一次，若想每个线程都进去，需实例化多个FutureTask。
 */
public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("callable"+Thread.currentThread().getName());
        return 666;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        FutureTask<Integer> futureTask1 = new FutureTask<>(myCallable);
        //多个线程调用同一futureTask只进去futureTask一次
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        new Thread(futureTask1).start();
        System.out.println("+++"+futureTask.get());
        System.out.println("---"+futureTask1.get());
    }
}
