package java8.wxf.concurrent.createThread;

/**
 * 实现runnable接口
 * 需要实现 run() 方法。
 * 通过 Thread 调用 start() 方法来启动线程。
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("Runnable implements thread");
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
