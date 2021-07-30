package java8.wxf.concurrent.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Executor 管理多个异步任务的执行，而无需程序员显式地管理线程的生命周期。这里的异步是指多个任务的执行互
 * 不干扰，不需要进行同步操作。
 *
 * 主要有三种 Executor：
 * CachedThreadPool：一个任务创建一个线程；
 * FixedThreadPool：所有任务只能使用固定大小的线程；
 * SingleThreadExecutor：相当于大小为 1 的 FixedThreadPool。
 *
 * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，
 * 则相当于调用每个线程的 interrupt() 方法。
 */
public class MyExecutor {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            try{
                Thread.sleep(2000);
                System.out.println("thread run..");
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> future = service.submit(()-> {
            System.out.println("submit");
        });
        future.cancel(true);
        service.shutdownNow();
        System.out.println("Main run");
    }
}
