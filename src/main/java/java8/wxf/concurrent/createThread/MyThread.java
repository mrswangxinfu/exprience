package java8.wxf.concurrent.createThread;

/**
 * 同样也是需要实现 run() 方法，因为 Thread 类也实现了 Runable 接口。
 * 当调用 start() 方法启动一个线程时，虚拟机会将该线程放入就绪队列中等待被调度，当一个线程被调度时会执行该
 * 线程的 run() 方法。
 */
public class MyThread extends Thread {
    //重写run方法
    public void run () {
        System.out.println("thread....");
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
