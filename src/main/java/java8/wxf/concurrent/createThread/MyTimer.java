package java8.wxf.concurrent.createThread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器内部使用Thread
 */
public class MyTimer {
    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();

        //前一次执行程序结束后 2000ms 后开始执行下一次程序（循环）
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Task1");
            }
        },0,2000);

        //只执行一次
        // 延迟1000ms执行程序
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Task2");
            }
        },1000);
        //前一次程序执行开始 后 2000ms后开始执行下一次程序（循环）
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                System.out.println("Task3");
            }
        },0,2000);
        Thread.sleep(10*1000);
        // 停止线程循环
        timer.cancel();
    }
}
