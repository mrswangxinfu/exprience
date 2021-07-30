package java8.wxf.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程
 * 会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
 * 它们都属于 Object 的一部分，而不属于 Thread。
 * 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同
 * 步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁
 *
 * wait() 和 sleep() 的区别:
 *   wait() 是 Object 的方法，而 sleep() 是 Thread 的静态方法；
 *   wait() 会释放锁，sleep() 不会。
 *
 *
 * 锁池:假设线程A已经拥有了某个对象(注意:不是类)的锁，而其它的线程想要调用这个对象的某个synchronized方法(或者synchronized块)，
 *     由于这些线程在进入对象的synchronized方法之前必须先获得该对象的锁的拥有权，但是该对象的锁目前正被线程A拥有，
 *      所以这些线程就进入了该对象的锁池中。
 * 等待池:假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁后，
 *    进入到了该对象的等待池中。
 * Reference：java中的锁池和等待池 然后再来说notify和notifyAll的区别
 *    如果线程调用了对象的 wait()方法，那么线程便会处于该对象的等待池中，
 *    等待池中的线程不会去竞争该对象的锁。当有线程调用了对象的 notifyAll()方法（唤醒所有 wait 线程）
 *    或 notify()方法（只随机唤醒一个 wait 线程），被唤醒的的线程便会进入该对象的锁池中，
 *    锁池中的线程会去竞争该对象锁。也就是说，调用了notify后只要一个线程会由等待池进入锁池，
 *    而notifyAll会将该对象等待池内的所有线程移动到锁池中，等待锁竞争优先级高的线程竞争到对象锁的概率大，
 *    假若某线程没有竞争到该对象锁，它还会留在锁池中，唯有线程再次调用 wait()方法，它才会重新回到等待池中。
 *    而竞争到对象锁的线程则继续往下执行，直到执行完了 synchronized 代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。
 * Reference：线程间协作：wait、notify、notifyAll

    综上，所谓唤醒线程，另一种解释可以说是将线程由等待池移动到锁池，
         notifyAll调用后，会将全部线程由等待池移到锁池，然后参与锁的竞争，
        竞争成功则继续执行，如果不成功则留在锁池等待锁被释放后再次参与竞争。
       而notify只会唤醒一个线程。有了这些理论基础，后面的notify可能会导致死锁，
       而notifyAll则不会的例子也就好解释了
 */
public class ThreadWait {
    public synchronized void before(String name) {
        System.out.println(name + ":before");
//        notify();
        notifyAll();
    }
    public synchronized void after(String name) {
        try {
            wait();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ":after");
    }

    public static void main(String[] args) {
        ThreadWait threadWait = new ThreadWait();
        ThreadWait threadWait1 = new ThreadWait();
        ExecutorService executor = Executors.newCachedThreadPool();

        executor.execute(() -> threadWait.after(Thread.currentThread().getName()));
        executor.execute(() -> threadWait.before(Thread.currentThread().getName()));
        executor.execute(() -> threadWait1.after(Thread.currentThread().getName()));
        executor.execute(() -> threadWait1.before(Thread.currentThread().getName()));
    }
}
