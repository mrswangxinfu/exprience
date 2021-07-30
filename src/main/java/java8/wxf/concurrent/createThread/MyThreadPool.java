package java8.wxf.concurrent.createThread;

import java.util.concurrent.*;

/**
 * 线程池创建线程
 *
 * 重点： Executors.newFixedThreadPool(int)、Executors.newCachedThreadPool()、Executors.newSingleThreadExecutor()
 *       它们的线程允许的最大数目是Integer.MAX_VALUE，newFixedThreadPool使用LinkedBlockingQueue  都会引发OOM
 *       故一个都不用，用ThreadPoolExecutor自定义
 *
 * 底层类：ThreadPoolExecutor    自定义根据CPU密集型还是IO密集型合理配置线程池
 *     七大参数：
 *        1、 corePoolSize: 线程池中常驻核心线程数。
 *        2、 maximumPoolSize: 线程池能容纳同时执行的最大线程数，此池必须大于等于1
 *        3、 keepAliveTime: 多余的空闲线程的存活时间
 *            当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime时，
 *            多余空闲线程会被销毁直到只剩下corePoolSize个线程为止。
 *        4、 unit: keepAliveTime的单位。
 *        5、 workQueue: 任务队列，被提交但尚未执行的任务队列。
 *        6、 threadFactory: 表示生成线程池中工作线程的线程工厂，用于创建线程，一般用默认的 即：Executors.defaultThreadFactory()
 *        7、 handler: 拒绝策略。表示当队列满了并且工作线程大于等于线程池的最大线程数
 *             AbortPolicy(默认策略): 直接抛RejectedExecutionException异常阻止系统运行。
 *             CallerRunsPolicy: 调用者运行（一种调节机制），该策略不抛弃任务也不抛异常，
 *                 若添加失败，那么主线程会自己调用执行器中的execute()方法执行该任务。
 *             DiscardOldestPolicy: 抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交
 *             DiscardPolicy: 直接丢弃任务，不予任何处理也不抛异常。如果允许任务丢失，这是最好的策略
 *             自定义策略：实现RejectedExecutionHandler接口，并将逻辑写在rejectedExecution方法内。
 *
 *       线程池流程：
 *           1、 在创建线程池后，等待提交过来的任务请求。
 *           2、当调用execute()添加一个任务时，线程池会做出如下判断：
 *              若正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务。
 *              若正在运行的线程数量大于等于corePoolSize，则将任务放入workQueue队列中。
 *              若队列满了且正在运行的线程数量还小于maximumPoolSize，则创建非核心线程数来执行。
 *              若队列满了且正在运行的线程数量大于等于maximumPoolSize，则线程池启动拒绝策略。
 *           3、当一个线程完成任务时，会从队列中取出下一个任务来执行。
 *           4、当一个线程无事可做且空闲时间超过keepAliveTime时，线程池会判断若当当前线程数量大于corePoolSize，那么这个线程就被停掉。
 */
public class MyThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ExecutorService executor2 = new ThreadPoolExecutor(2,5,30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        // 创建10个线程
        for (int i=0;i<10;i++) {
            executor.execute(() -> {
                System.out.println("线程："+Thread.currentThread().getName());
            });
        }
        executor.shutdown();
    }
}
