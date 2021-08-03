package java8.wxf.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 进行反编译从字节码中可以知道synchronized如何获取monitor对象
 *
 *  对象： 是放在堆内存中的，对象大致可以分为三个部分，分别是对象头，实例变量和填充字节
 * 对象头，主要包括两部分1. Mark Word （标记字段），2.Klass Pointer（类型指针）。
 * Klass Point 是对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例
 * （即指向方法区类的模版信息）。Mark Word用于存储对象自身的运行时数据
 * 实例变量，存放类的属性数据信息，包括父类的属性信息，这部分内存按4字节对齐
 * 填充数据，由于虚拟机要求对象起始地址必须是8字节的整数倍。填充数据不是必须存在的，仅仅是为了字节对齐
 *
 * synchronized不论是修饰方法还是代码块，都是通过持有修饰对象的锁来实现同步，
 * 那么synchronized锁对象是存在哪里的呢？答案是存在锁对象的对象头Mark Word
 *
 *           synchronized锁有四种状态，无锁，偏向锁，轻量级锁，重量级锁，
 *           这几个状态会随着竞争状态逐渐升级，锁可以升级但不能降级，但是偏向锁状态可以被重置为无锁状态
 *
 *
 * 1.操作系统需要两种CPU状态
 * 内核态（Kernel Mode）：运行操作系统程序，操作硬件
 * 用户态（User Mode）：运行用户程序
 * 2.指令划分
 * 特权指令：只能由操作系统使用、用户程序不能使用的指令。 举例：启动I/O 内存清零 修改程序状态字 设置时钟 允许/禁止终端 停机
 *
 * 非特权指令：用户程序可以使用的指令。 举例：控制转移 算数运算 取数指令 访管指令（使用户程序从用户态陷入内核态）
 *
 * 3.特权级别
 * 特权环：R0、R1、R2和R3
 *
 * R0相当于内核态，R3相当于用户态；
 *
 * 不同级别能够运行不同的指令集合；
 *
 * 4.CPU状态之间的转换
 * 用户态—>内核态：唯一途径是通过中断、异常、陷入机制（访管指令）
 *
 * 内核态—>用户态：设置程序状态字PSW
 *
 * 5.内核态与用户态的区别
 * 内核态与用户态是操作系统的两种运行级别，当程序运行在3级特权级上时，就可以称之为运行在用户态。因为这是最低特权级，是普通的用户进程运行的特权级，大部分用户直接面对的程序都是运行在用户态；
 *
 * 当程序运行在0级特权级上时，就可以称之为运行在内核态。
 *
 * 运行在用户态下的程序不能直接访问操作系统内核数据结构和程序。当我们在系统中执行一个程序时，大部分时间是运行在用户态下的，在其需要操作系统帮助完成某些它没有权力和能力完成的工作时就会切换到内核态（比如操作硬件）。
 *
 * 这两种状态的主要差别是
 *
 * 处于用户态执行时，进程所能访问的内存空间和对象受到限制，其所处于占有的处理器是可被抢占的
 * 处于内核态执行时，则能访问所有的内存空间和对象，且所占有的处理器是不允许被抢占的。
 * 6. 通常来说，以下三种情况会导致用户态到内核态的切换
 * 系统调用
 * 这是用户态进程主动要求切换到内核态的一种方式，用户态进程通过系统调用申请使用操作系统提供的服务程序完成工作。比如前例中fork()实际上就是执行了一个创建新进程的系统调用。
 * 而系统调用的机制其核心还是使用了操作系统为用户特别开放的一个中断来实现，例如Linux的int 80h中断。
 *
 * 用户程序通常调用库函数，由库函数再调用系统调用，因此有的库函数会使用户程序进入内核态（只要库函数中某处调用了系统调用），有的则不会。
 *
 * 异常
 * 当CPU在执行运行在用户态下的程序时，发生了某些事先不可知的异常，这时会触发由当前运行进程切换到处理此异常的内核相关程序中，也就转到了内核态，比如缺页异常。
 *
 * 外围设备的中断
 * 当外围设备完成用户请求的操作后，会向CPU发出相应的中断信号，这时CPU会暂停执行下一条即将要执行的指令转而去执行与中断信号对应的处理程序，
 *
 * 如果先前执行的指令是用户态下的程序，那么这个转换的过程自然也就发生了由用户态到内核态的切换。比如硬盘读写操作完成，系统会切换到硬盘读写的中断处理程序中执行后续操作等。
 *
 * 这3种方式是系统在运行时由用户态转到内核态的最主要方式，其中系统调用可以认为是用户进程主动发起的，异常和外围设备中断则是被动的。
 *
 *
 * 对象锁与全局锁的区别： 对象锁只能锁住一个对象，全局锁是锁住该类的所有对象
 */
public class MySynchronized {
    //测试同步同一对象,不同对象则会异步----使用对象锁
    public void testObject (String name) {
        synchronized (this) {
            int i=0;
            while (i<10) {
                System.out.println(name+":"+i++);
            }
        }
    }
    // 测试同一对象同步方法
    public synchronized void testMethod(String name) {
        int i=0;
        while (i<10) {
            System.out.println(name+":"+i++);
        }
    }
    // 测试同步类---使用全局锁（类锁）
    public void testClass(String name) {
        synchronized (MySynchronized.class) {
            int i=0;
            while (i<10) {
                System.out.println(name+":"+i++);
            }
        }
    }
    // synchronized修饰的静态方法锁定的也是这个类的所有对象---效果和全局锁一致
    public synchronized static void testStaticMethod(String name) {
        int i=0;
        while (i<10) {
            System.out.println(name+":"+i++);
        }
    }

    // 隐式可重入锁
    // 可重入锁的工作原理很简单，就是用一个计数器来记录锁被获取的次数，获取锁一次计数器+1，释放锁一次计数器-1，当计数器为0时，表示锁可用。
    public void reEntrantLock() {
        new Thread(() -> {
            synchronized (this) {
                System.out.println("外层同步调用");
                synchronized (this) {
                    System.out.println("内层同步调用");
                }
            }
        }).start();
    }
    public static void main(String[] args) {
        MySynchronized mySynchronized = new MySynchronized();
        MySynchronized mySynchronized1 = new MySynchronized();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(()->{
//            mySynchronized.testObject(Thread.currentThread().getName());
//            mySynchronized.testMethod(Thread.currentThread().getName());
//            mySynchronized.testClass(Thread.currentThread().getName());
        });
        executor.execute(()->{
//            mySynchronized.testObject(Thread.currentThread().getName());
//            mySynchronized1.testObject(Thread.currentThread().getName());
//            mySynchronized.testMethod(Thread.currentThread().getName());
//            mySynchronized1.testClass(Thread.currentThread().getName());
        });

        mySynchronized.reEntrantLock();
    }
}
