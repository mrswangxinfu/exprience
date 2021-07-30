单例模式（Singleton）

效果
1. 保证一个类只有一个实例，并且提供一个访问该实例的全局访问点。

2. 由于单例模式只生成一个实例，减少了系统性能开销，当一个对象的产生需要比较多的资源时，如读取配置、产生其他依赖对象时，则可以通过在应用启动时直接产生一个单例对象，然后永久驻留内存的方式来解决。

分类：
1. 常见实现方式。

    a) 饿汉式：线程安全，调用效率高，不能延时加载。

    b) 懒汉式：线程安全，调用效率不高，可延时加载。

2. 其他实现方式。

    a) 双重检测锁式：由于JVM底层内部模型原因，偶尔出问题，不建议使用。

    b) 静态内部类式：线程安全，调用效率高，可延时加载。

    c) 枚举单例：线程安全，调用效率高，不能延时加载。

3. 选择方法。

    a) 单例对象占用资源少，不需要延时加载：枚举式好于饿汉式；

    b) 单例对象占用资源多，需要延时加载：静态内部类好于懒汉式。
    
    代码（饿汉式）
    
    1. Singleton.java
    
    
     1 public class Singleton {
     2 
     3     // 类初始化时立即创建对象
     4     private static final Singleton instance = new Singleton();
     5     
     6     // 私有化构造器
     7     private Singleton() {
     8         if (instance != null) {
     9             throw new RuntimeException();
    10         }
    11     }
    12     
    13     public static Singleton getInstance() {
    14         return instance;
    15     }
    16     
    17 }
    
   2. Client.java
    
    
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Singleton singleton1 = Singleton.getInstance();
    5         Singleton singleton2 = Singleton.getInstance();
    6         System.out.println(singleton1 == singleton2);
    7     }
    8 
    9 }
    
    
    
   代码（懒汉式）
   1. Singleton.java
    
    
     1 public class Singleton {
     2 
     3     private static Singleton instance;
     4     
     5     // 私有化构造器
     6     private Singleton() {
     7     }
     8     
     9     // 同步方法
    10     public static synchronized Singleton getInstance() {
    11         if (instance == null) {
    12             // 延时加载
    13             instance = new Singleton();
    14         }
    15         return instance;
    16     }
    17     
    18 }
    
    
   2. Client.java
    
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Singleton singleton1 = Singleton.getInstance();
    5         Singleton singleton2 = Singleton.getInstance();
    6         System.out.println(singleton1 == singleton2);
    7     }
    8 
    9 }
    
    
   代码（双重检测锁式）
   1. Singleton.java
    
     1 public class Singleton {
     2 
     3     private static volatile Singleton instance;
     4     
     5     // 私有化构造器
     6     private Singleton() {
     7     }
     8     
     9     public static Singleton getInstance() {
    10         if (instance == null) {
    11             // 第一次创建时同步
    12             synchronized (Singleton.class) {
    13                 if (instance == null) {
    14                     // 延时加载
    15                     instance = new Singleton();
    16                 }
    17             }
    18         }
    19         return instance;
    20     }
    21     
    22 }


   2. Client.java
   
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Singleton singleton1 = Singleton.getInstance();
    5         Singleton singleton2 = Singleton.getInstance();
    6         System.out.println(singleton1 == singleton2);
    7     }
    8 
    9 }
    
   代码（静态内部类式）
   1. Singleton.java
    
     1 public class Singleton {
     2     
     3     // 初始化外部类时不会立即初始化内部类
     4     private static class SingletonInstance {
     5         private static final Singleton instance = new Singleton();
     6     }
     7 
     8     // 私有化构造器
     9     private Singleton() {
    10     }
    11     
    12     public static Singleton getInstance() {
    13         return SingletonInstance.instance;
    14     }
    15     
    16 }


  2. Client.java
    
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Singleton singleton1 = Singleton.getInstance();
    5         Singleton singleton2 = Singleton.getInstance();
    6         System.out.println(singleton1 == singleton2);
    7     }
    8 
    9 }
    
    
    
  代码（枚举单例）
  1. Singleton.java
  
    1 public enum Singleton {
    2     
    3     // 枚举本身就是单例
    4     INSTANCE;
    5     
    6     // 添加需要的方法
    7     public void method() {
    8     }
    9     
    10 }


  2. Client.java
  
    1 public class Client {
    2 
    3     public static void main(String[] args) {
    4         Singleton singleton1 = Singleton.INSTANCE;
    5         Singleton singleton2 = Singleton.INSTANCE;
    6         System.out.println(singleton1 == singleton2);
    7     }
    8 
    9 }  
    
    
    
   代码（使用反射的破解与防御）
   1. Singleton.java
    
     1 public class Singleton {
     2 
     3     // 类初始化时立即创建对象
     4     private static final Singleton instance = new Singleton();
     5     
     6     // 私有化构造器
     7     private Singleton() {
     8         // 防御：再次创建时抛出异常
     9         if (instance != null) {
    10             throw new RuntimeException();
    11         }
    12     }
    13     
    14     public static Singleton getInstance() {
    15         return instance;
    16     }
    17     
    18 }


   2. Client.java
 
     1 import java.lang.reflect.Constructor;
     2 
     3 public class Client {
     4 
     5     public static void main(String[] args) throws Exception {
     6         Singleton singleton1 = Singleton.getInstance();
     7         
     8         Class<Singleton> clazz = Singleton.class;
     9         Constructor<Singleton> constructor = clazz.getDeclaredConstructor();
    10         constructor.setAccessible(true);
    11         Singleton singleton2 = constructor.newInstance();
    12         System.out.println(singleton1 == singleton2);
    13     }
    14 
    15 }

    
   代码（使用序列化的破解与防御）
   1. Singleton.java
    
    
     1 import java.io.Serializable;
     2 
     3 public class Singleton implements Serializable {
     4 
     5     private static final long serialVersionUID = -3230831923851678463L;
     6     
     7     // 类初始化时立即创建对象
     8     private static final Singleton instance = new Singleton();
     9     
    10     // 私有化构造器
    11     private Singleton() {
    12     }
    13     
    14     public static Singleton getInstance() {
    15         return instance;
    16     }
    17     
    18     // 防御：反序列化时，直接返回该方法的返回值
    19     private Object readResolve() {
    20         return instance;
    21     }
    22     
    23 }


   2. Client.java
    
     1 import java.io.File;
     2 import java.io.FileInputStream;
     3 import java.io.FileOutputStream;
     4 import java.io.ObjectInputStream;
     5 import java.io.ObjectOutputStream;
     6 
     7 public class Client {
     8 
     9     public static void main(String[] args) throws Exception {
    10         Singleton singleton1 = Singleton.getInstance();
    11         
    12         File tempFile = new File("D:/test");
    13         ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile));
    14         oos.writeObject(singleton1);
    15         oos.close();
    16         ObjectInputStream ios = new ObjectInputStream(new FileInputStream(tempFile));
    17         Singleton singleton2 = (Singleton) ios.readObject();
    18         ios.close();
    19         System.out.println(singleton1 == singleton2);
    20         
    21     }
    22 
    23 }
    
    
    
应用场景
   
  1. Windows的Task Manager（任务管理器）。

  2. Windows的Recycle Bin（回收站）。

  3. 项目中，读取配置文件的类，一般只有一个对象，没必要每次创建。

  4. 数据库连接池。

  5. Application是单例的典型应用（Servlet编程）。

  6. Spring中，每个Bean默认是单例的。

  7. 每个Servlet是单例。

  8. Spring MVC中，控制器对象是单例的。
    