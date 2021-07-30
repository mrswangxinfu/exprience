package java8.wxf.concurrent.otherModule;

import lombok.SneakyThrows;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 在介绍 Callable 时我们知道它可以有返回值，返回值通过 Future 进行封装。FutureTask 实现了 RunnableFuture 接
 * 口，该接口继承自 Runnable 和 Future 接口，这使得 FutureTask 既可以当做一个任务执行，也可以有返回值。
 * public class FutureTask<V> implements RunnableFuture<V> public interface RunnableFuture<V> extends Runnable, Future<V>
 * FutureTask 可用于异步获取执行结果或取消执行任务的场景。当一个计算任务需要执行很长时间，那么就可以用
 * FutureTask 来封装这个任务，主线程在完成自己的任务之后再去获取结果。
 */
public class MyFutureTask  {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int res = 0;
                for (int i=0;i<100;i++) {
                    Thread.sleep(10);
                    System.out.println("任务线程："+Thread.currentThread().getName());
                    res+=10;
                }
                return res;
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("other thread run...");
                Thread.sleep(1000);
            }
        }).start();
        System.out.println(futureTask.get());
        System.out.println("主线程: "+Thread.currentThread().getName());
    }
}
