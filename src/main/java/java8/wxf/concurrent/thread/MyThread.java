package java8.wxf.concurrent.thread;

/**
 * Daemon
 * 守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
 * 当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。
 * main() 属于非守护线程。
 * 使用 setDaemon() 方法将一个线程设置为守护线程。
 * public static void main(String[] args) { Thread thread = new Thread(new MyRunnable()); thread.setDaemon(true); }
 * sleep()
 * Thread.sleep(millisec) 方法会休眠当前正在执行的线程，millisec 单位为毫秒。
 * sleep() 可能会抛出 InterruptedException，因为异常不能跨线程传播回 main() 中，因此必须在本地进行处理。线程
 * 中抛出的其它异常也同样需要在本地进行处理。
 * public void run() { try {Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); } }
 * yield()
 * 对静态方法 Thread.yield() 的调用声明了当前线程已经完成了生命周期中最重要的部分，可以切换给其它线程来执
 * 行。该方法只是对线程调度器的一个建议，而且也只是建议具有相同优先级的其它线程可以运行。
 * public void run() { Thread.yield(); }
 * 四、中断
 * 一个线程执行完毕之后会自动结束，如果在运行过程中发生异常也会提前结束。
 * InterruptedException
 * 通过调用一个线程的 interrupt() 来中断该线程，如果该线程处于阻塞、限期等待或者无限期等待状态，那么就会抛
 * 出 InterruptedException，从而提前结束该线程。但是不能中断 I/O 阻塞和 synchronized 锁阻塞。
 * 对于以下代码，在 main() 中启动一个线程之后再中断它，由于线程中调用了 Thread.sleep() 方法，因此会抛出一个
 * InterruptedException，从而提前结束线程，不执行之后的语句。
 * public class InterruptExample { private static class MyThread1 extends Thread {
 *   @Override public void run() {
 *    try {Thread.sleep(2000); System.out.println("Thread run"); } catch (InterruptedException e) { e.printStackTrace(); } } } }
 *    public static void main(String[] args) throws InterruptedException { Thread thread1 = new MyThread1(); thread1.start(); thread1.interrupt(); System.out.println("Main run"); }
 * Main run java.lang.InterruptedException: sleep interrupted at java.lang.Thread.sleep(Native Method) at InterruptExample.lambda$main$0(InterruptExample.java:5) at InterruptExample$$Lambda$1/713338599.run(Unknown Source) at java.lang.Thread.run(Thread.java:745)
 * interrupted()
 * 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作，那么
 * 调用线程的 interrupt() 方法就无法使线程提前结束。
 * 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。因此可以在循环体中使
 * 用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
 */
public class MyThread {
    public MyThread(th o) {
        o.ce();
    }

    private static class ThreadDemo extends Thread {
        @Override
        public void run() {
            int i = 0;
            while (!interrupted()) {
                System.out.println(i++);
            }
            System.out.println("ending");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        new MyThread(()->{
            System.out.println("ooo");
        });
        threadDemo.start();
        threadDemo.sleep(2);
        threadDemo.interrupt();
    }
    interface th{
        void ce();
    }
}
