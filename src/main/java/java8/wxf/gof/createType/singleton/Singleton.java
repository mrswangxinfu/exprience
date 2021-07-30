package java8.wxf.gof.createType.singleton;

/**
 * 单例模式
 */
public class Singleton {

    //1、懒汉式，线程不安全
//    //唯一的对象引用
//    private static Singleton uniqueInstance;
//
//    //私有构造方法，所以外部不能通过new创建对象
//    private Singleton(){
//
//    }
//
//    //获取对象的静态方法
//    public static Singleton getUniqueInstance(){
//
//        if(uniqueInstance==null){
//            uniqueInstance=new Singleton();
//        }
//        return uniqueInstance;
//    }

    //2、饿汉式线程安全
//    private static Singleton uniqueInstance=new Singleton();
//
//    private Singleton(){
//        if (null!=uniqueInstance) {
//            throw new RuntimeException();
//        }
//    }
//    public static Singleton getUniqueInstance(){
//        return uniqueInstance;
//    }

    //3、懒汉式线程安全
//    private static Singleton uniqueInstance;
//
//    //私有构造方法，所以外部不能通过new创建对象
//    private Singleton(){
//
//    }
//
//    //获取对象的静态方法
//    public static synchronized Singleton getUniqueInstance(){
//
//        if(uniqueInstance==null){
//            uniqueInstance=new Singleton();
//        }
//        return uniqueInstance;
//    }

    //4、双重校验锁-线程安全

    //第二次校验，也就是第二次判断if(uniqueInstance==null),是为了防止二次创建实列，
    // 我们假设一种状况，当singleton还未被创建的时候，线程r1 调用了getUniqueInstance 方法，
    // 由于此时的singleton 为空，则可以进入第一层判断，线程r1正准备继续执行，此时，线程r2抢占cpu资源，
    // 此时r2也调用了getUniqueInstance 方法，同理线程r1并没有实例化singleton，
    // 线程r2也可以进去判断，然后继续往下执行，进入到同步代码块，进入第二层判断，完成了singleton 的创建，并分配空间，
    // r2线程运行周期结束。执行任务又回到了r1,如果没有第二层判断，线程r1 也会创建一个实列(r2线程已经创建一个实列，
    // 第二层判断为false)，这样就完全避免掉多线程环境下会创建多个实列的的问题。
    //使用volatile关键字修饰防止jvm指令重排
       //1）保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这变量的新值对其他要进行读取的线程来说是立即可见的。
       //2）禁止进行指令重排序。
//    private static volatile Singleton uniqueInstance;
//
//    //私有构造方法，所以外部不能通过new创建对象
//    private Singleton(){
//
//    }
//    //获取对象的静态方法
//    public static Singleton getUniqueInstance(){
//
//        if(uniqueInstance==null){
//            synchronized(Singleton.class){
//                if(uniqueInstance==null){
//                    uniqueInstance=new Singleton();
//                }
//            }
//        }
//        return uniqueInstance;
//    }

    //5、静态内部类实现

//    private Singleton(){}
//
//    //使用静态内部类，当Singleton类被加载时，SingletonHolder类没有被加载进内存
//    //只有当调用getUniqueInstance()时才被加载
//    private static class SingletonHolder{
//        //被final修饰的变量不可改变，只能赋值一次，确保INSTANCE只能被实例化一次
//        private static final Singleton INSTANCE=new Singleton();
//    }
//    public Singleton getUniqueInstance(){
//        return SingletonHolder.INSTANCE;
//    }
    //6、枚举实现
    //见Singleton_enum枚举类
}
