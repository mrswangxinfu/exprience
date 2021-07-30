package java8.wxf.test;

import java8.wxf.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * JVM: 方法区和堆是线程---共享，
 *      java虚拟机栈、本地方法栈、程序计数器是线程---私有
 *
 *      方法区保存类信息
 *      栈管运行，堆管存储
 * 内存分配方式： 指针碰撞，空闲列表。
 * 双亲委派机制：任何类都不会直接使用用户自定义的类加载器，而是全部传送到启动类加载器，最先从最高级加载器--启动类加载器开始找，
 * 没有再找子类加载器（扩展类加载器），再没有再找应用程序加载器...这样保证先加载所有jdk下的源类再去加载用户自定义的类
 *
 * 沙箱安全机制：保证用户自定义的类不污染源码（即，即使用户自定义的类与源码类名字完全相同也会只加载源码类而不会加载用户自定义的类）
 *
 * 线程是进程的部分，进程是操作系统拥有，所以线程也是操作系统所有与语言无关，java是使用native访问操作系统
 *
 * register: 寄存器
 *
 * GC四大算法：引用计数法、复制算法、标记清除算法、标记压缩算法（标记--清除--压缩）
 * 四个垃圾收集器：serial(串行)、 parallel(并行)、 CMS(并发)、 G1
 */

public class TestJVM<T> {
    private T object;
    private int a=0;
    public synchronized static void test() {}
    public static void main(String[] args) throws ClassNotFoundException {
        synchronized (TestJVM.class){
            test();
        }
//        Person person=new Person();
//        System.out.println(person);
//        Person pe=person;
//        String hj="123";
//        System.out.println(hj.getClass());
//        System.out.println(pe.getClass().getName());
//        trans(person);
//        System.out.println(person);
//        System.out.println(pe==person);
//        System.out.println(Class.forName("java8.wxf.test.Test").getName());
//        System.out.println();
//
//
//        Class c4 = "person".getClass();
//        Constructor[] constructors ;
//        constructors = c4.getDeclaredConstructors();
//        System.out.println(constructors);
//        int i=0;
//
//        System.out.println("处理器核数："+Runtime.getRuntime().availableProcessors());
//        System.out.println("JVM空闲大小："+Runtime.getRuntime().freeMemory()+"字节---="+Runtime.getRuntime().freeMemory()/(1024*1024)+"MB");
//        System.out.println("JVM堆最大内存数-xmx："+Runtime.getRuntime().maxMemory()+"字节---="+Runtime.getRuntime().maxMemory()/(1024*1024)+"MB");
//        System.out.println("JVM默认初始堆内存Total_MEMORY-xms:"+Runtime.getRuntime().totalMemory()+"字节---="+ Runtime.getRuntime().totalMemory()/(1024*1024)+"MB");
//        testOOM();
//        System.gc();
//        int ox=0x00F1;
//        System.out.println(ox);
        Object  object=new Object();
        System.out.println("Bootstrap类加载器是所有加载器祖先，C++写的，不能访问:\n"+object.getClass().getClassLoader());


        TestJVM test=new TestJVM();
        System.out.println("BootstrapLoader启动类加载器"+test.getClass().getClassLoader().getParent().getParent());
        System.out.println("ExtClassLoader扩展类加载器"+test.getClass().getClassLoader().getParent());
        System.out.println("AppClassLoader应用程序类加载器"+test.getClass().getClassLoader());
//        ClassLoader classLoader=new ClassLoader(){} ;
//        System.out.println("父加载器是：  "+classLoader.getParent());
//        System.out.println(classLoader.getParent()+"的父加载器是：  "+classLoader.getParent().getParent());
//        System.out.println(classLoader.getParent().getParent()+"   的父加载器是：  "+classLoader.getParent().getParent().getParent());
//
//        System.out.println(classLoader.getParent().getClass().getName());
    }

    /**
     * 设值
     * @param person
     */
    public static void trans(Person person){
        person.setAge("20");
        person.setId("1564");
        person.setName("张三");
    }
    public interface IThreadTest{
        ThreadPoolExecutor thread= new ThreadPoolExecutor(
            10,
                    15,
                    60L,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
        r -> new Thread(r, "DrawImage-" + r.hashCode()),
                new ThreadPoolExecutor.AbortPolicy()
        );

       public static final ThreadPoolExecutor OP_CONSTANT = new ThreadPoolExecutor(
               10,
               15,
               20L,
               TimeUnit.SECONDS,
               new LinkedBlockingQueue<>(10),
               r -> new Thread(r, "OP-" + r.hashCode()),
               new ThreadPoolExecutor.AbortPolicy()
       );
    }

    /**
     *不断创建对象并使其不能回收即可引发 OOM
     */
    public  static  void testOOM(){
//        System.out.println(465);
        List<TestJVM>list=new ArrayList<>();
        List<byte[]>list1=new ArrayList<>();
        while (true){
//           Test test= new Test();
//           list.add(test);
            byte[] bytes=new byte[1024];
            list1.add(bytes);
        }
//    byte[] by=new byte[1000*1024*1024];// new 一个内存大小为1000MB的 对象
    }
}
