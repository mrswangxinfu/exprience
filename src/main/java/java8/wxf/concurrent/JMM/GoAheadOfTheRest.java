package java8.wxf.concurrent.JMM;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JMM先行规则  --先行发生（happens-before）
 *
 * 1. 单一线程原则
 *   Single Thread rule
 *   在一个线程内，在程序前面的操作先行发生于后面的操作。
 * 2. 管程锁定规则
 *   Monitor Lock Rule
 *   一个 unlock 操作先行发生于后面对同一个锁的 lock 操作
 * 3. volatile 变量规则
 *   Volatile Variable Rule
 *   对一个 volatile 变量的写操作先行发生于后面对这个变量的读操作。
 * 4. 线程启动规则
 *   Thread Start Rule
 *   Thread 对象的 start() 方法调用先行发生于此线程的每一个动作。
 * 5. 线程加入规则
 *   Thread Join Rule
 *   Thread 对象的结束先行发生于 join() 方法返回。
 * 6. 线程中断规则
 *   Thread Interruption Rule
 *   对线程 interrupt() 方法的调用先行发生于被中断线程的代码检测到中断事件的发生，可以通过 interrupted() 方法检
 *   测到是否有中断发生。
 * 7. 对象终结规则
 *   Finalizer Rule
 *   一个对象的初始化完成（构造函数执行结束）先行发生于它的 finalize() 方法的开始。
 * 8. 传递性
 *   Transitivity
 *   如果操作 A 先行发生于操作 B，操作 B 先行发生于操作 C，那么操作 A 先行发生于操作 C。
 *
 *
 *
 * 程序次序规则（Program Order Rule）：在一个线程内，按照程序代码顺序，书写在前面的操作先行发生于书写在后面的操作。
 *                                   准确地说，应该是控制流顺序而不是程序代码顺序，因为要考虑分支、循环等结构。
 * 管程锁定规则（Monitor Lock Rule）：一个unlock操作先行发生于后面对同一个锁的lock操作。
 *                                  这里必须强调的是同一个锁，而“后面”是指时间上的先后顺序。
 * volatile变量规则（Volatile Variable Rule）：对一个volatile变量的写操作先行发生于后面对这个变量的读操作，
 *                                           这里的“后面”同样是指时间上的先后顺序。
 * 线程启动规则（Thread Start Rule）：Thread对象的start()方法先行发生于此线程的每一个动作。
 * 线程终止规则（Thread Termination Rule）：线程中的所有操作都先行发生于对此线程的终止检测，
 *                                        我们可以通过Thread.join()方法结束、Thread.isAlive（）的返回值等手段检测到线程已经终止执行。
 * 线程中断规则（Thread Interruption Rule）：对线程interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生，
 *                                         可以通过Thread.interrupted()方法检测到是否有中断发生。
 * 对象终结规则（Finalizer Rule）：一个对象的初始化完成（构造函数执行结束）先行发生于它的finalize()方法的开始。
 * 传递性（Transitivity）：如果操作A先行发生于操作B，操作B先行发生于操作C，那就可以得出操作A先行发生于操作C的结论。
 */
public class GoAheadOfTheRest {
    //  2管程锁定规则
    //Monitor Lock Rule
    //一个 unlock 操作先行发生于后面对同一个锁的 lock 操作。
    private ReentrantLock lock = new ReentrantLock();

    private static volatile int res = 0;

    public int get() {
        return res;
    }
    public void setRes (int res) {
        this.res = res;
    }
    public void run () {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName());
        }finally {
            System.out.println("unlock");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        GoAheadOfTheRest rest = new GoAheadOfTheRest();
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 2管程锁定规则
//        for (int i=0;i<5;i++) {
//            executorService.execute(() -> {
//                rest.run();
//            });
//        }

        executorService.execute(() -> {
            rest.setRes(3);
            System.out.println(Thread.currentThread().getName()+"写res：" + res);
        });
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName()+"读res：" + rest.get());
        });


    }
}
