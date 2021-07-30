package java8.wxf.concurrent.otherModule;

import java.util.concurrent.*;

/**
 * ForkJoin
 * 主要用于并行计算中，和 MapReduce 原理类似，都是把大的计算任务拆分成多个小任务并行计算。
 *
 * ForkJoin 使用 ForkJoinPool 来启动，它是一个特殊的线程池，线程数量取决于 CPU 核数。
 * public class ForkJoinPool extends AbstractExecutorService
 * ForkJoinPool 实现了工作窃取算法来提高 CPU 的利用率。每个线程都维护了一个双端队列，用来存储需要执行的任
 * 务。工作窃取算法允许空闲的线程从其它线程的双端队列中窃取一个任务来执行。窃取的任务必须是最晚的任务，避
 * 免和队列所属线程发生竞争。例如下图中，Thread2 从 Thread1 的队列中拿出最晚的 Task1 任务，Thread1 会拿出
 * Task2 来执行，这样就避免发生竞争。但是如果队列中只有一个任务时还是会发生竞争。
 */
public class MyForkJoin extends RecursiveTask {
    private final int threshold = 5;
    private int first;
    private int last;
    public MyForkJoin (int first, int last) {
        this.first = first;
        this.last = last;
    }
    @Override
    protected Object compute() {
        int res = 0;
        if (last - first <= threshold) {
            // 若任务足够小则可以直接计算
            for (int i=first; i<last; i++) {
                res += i;
            }
        } else {
            // 否则拆分任务
            int middle = first + (last - first)/2;
            MyForkJoin leftTask = new MyForkJoin(first, middle);
            MyForkJoin rightTask = new MyForkJoin(middle+1, last);
            leftTask.fork();
            rightTask.fork();
            res = (int)leftTask.join()+(int)rightTask.join();
        }
        return res;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyForkJoin myForkJoin = new MyForkJoin(6, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask future = forkJoinPool.submit(myForkJoin);
        System.out.println(future.get());
    }
}
