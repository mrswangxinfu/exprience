##java对象
###一、加载过程
例子：

例如我想要加载一个Demo 类：

* 1、首先会先启动 JVM 虚拟机（JVM 虚拟机底层是由 C++ 来实现的，在 windows 下启动即为 java.exe 方法调用 jvm.dll 创建 JVM）
* 2、由 JVM 虚拟机创建 引导类加载器（同样底层为 C++ 实现）
* 3、该引导类加载器会去创建 JVM 启动类 sun.misc.Launcher 类实例。
* 4、通过调用 sun.misc.Launcher.getLauncher() 方法，该方法会返回 Launcher 类内部静态变量： private static Launcher launcher = new Launcher()。（ 在 new Launcher() 的过程中会创建运行类自己的类加载器。这里可以直接理解为 AppClassLoader 应用类加载器。）
* 5、通过 launcher.getClassLoader() 方法获取该类加载器 。
* 6、通过调用 classLoader.getClass(“com.test.Demo”) 方法加载 Demo 类实例。
* 7、类加载成功后由 JVM 发起调用，调用要执行类的主方法入口执行程序逻辑
* 8、执行结束， 销毁 JVM。




Java类从被虚拟机加载开始，到卸载出内存为止，
它的整个生命周期包括：加载（Loading）、验证（Verification）、
准备（Preparation）、解析（Resolution）、初始化（Initialization）、
使用（Using）和卸载（Unloading）7个阶段；其中验证、
准备和解析又统称为连接（Linking）阶段。通过JVM参数中加-verbose:class，可以在应用启动的时候打印类加载的过程。
以下阶段顺序执行
* 1、加载阶段
  *  1、通过一个类的全限定名（包名与类名）来获取定义此类的二进制字节流（Class文件）。而获取的方式，可以通过jar包、war包、网络中获取、JSP文件生成等方式。
    
  *  2、将这个字节流所代表的静态存储结构转化为方法区的运行时数据结构。这里只是转化了数据结构，并未合并数据。（方法区就是用来存放已被加载的类信息，常量，静态变量，编译后的代码的运行时内存区域）
    
  *  3、在内存中生成一个代表这个类的java.lang.Class对象，作为方法区这个类的各种数据的访问入口。这个Class对象并没有规定是在Java堆内存中，它比较特殊，虽为对象，但存放在方法区中。
   
* 2、验证阶段
  * 验证被加载后的类是否有正确的结构，类数据是否会符合虚拟机的要求，确保不会危害虚拟机安全。
    *  1 . 文件格式验证：主要验证字节流是否符合Class文件格式规范，并且能被当前版本的虚拟机处理。
    *  2 . 元数据验证：主要对字节码描述的信息进行语义分析，以保证其提供的信息符合Java语言规范的要求。
    *  3 . 字节码验证：主要是通过数据流和控制流分析，确定程序语义是合法的、符合逻辑的。在第二阶段对元数据信息中的数据类型做完校验后，字节码验证将对类的方法体进行校验分析，保证被校验类的方法在运行时不会做出危害虚拟机安全的事件。
    *  4 . 符号引用验证：最后一个阶段的校验发生在虚拟机将符号引用转化为直接引用的时候，这个转化动作将在连接的第三阶段解析阶段发生。符号引用是对类自身以外（常量池中的各种符号引用）的信息进行匹配校验。
  * 验证阶段非常重要，但不一定必要，如果所有代码极影被反复使用和验证过，那么可以通过虚拟机参数-Xverify: none来关闭验证，加速类加载时间。
  
* 3、准备阶段
  * 为类的静态变量（static filed）在方法区分配内存，并赋默认初值（0值或null值）。如static int a = 100;
    静态变量a就会在准备阶段被赋默认值0。
    对于一般的成员变量是在类实例化时候，随对象一起分配在堆内存中。
    另外，静态常量（static final filed）会在准备阶段赋程序设定的初值，如static final int a = 666;  静态常量a就会在准备阶段被直接赋值为666，对于静态变量，这个操作是在初始化阶段进行的。

* 4、解析阶段
  * 解析阶段(将类的二进制数据中的符号引用换为直接引用)是把常量池内的符号引用替换成直接引用的过程，符号引用就是Class文件中的CONSTANT_Class_info、** CONSTANT_Fieldref_info、CONSTANT_Methodref_info**等类型的常量。下面我们看符号引用和直接引用的定义。
    
    * 符号引用（Symbolic References）：符号引用以一组符号来描述所引用的目标，符号可以是任何形式的字面量，只要可以唯一定位到目标即可。符号引用于内存布局无关，所以所引用的对象不一定需要已经加载到内存中。各种虚拟机实现的内存布局可以不同，但是接受的符号引用必须是一致的，因为符号引用的字面量形式已经明确定义在Class文件格式中。
    
    * 直接引用（Direct References）：直接引用时直接指向目标的指针、相对偏移量或是一个能间接定位到目标的句柄。直接引用和虚拟机实现的内存布局相关，同一个符号引用在不同虚拟机上翻译出来的直接引用一般不会相同。如果有了直接引用，那么它一定已经存在于内存中了。
  * 这是所谓的静态链接过程(类加载期间完成)，动态链接是在程序运行期间完成的将符号引用替换为直接引用.
* 5、初始化阶段
  * 类初始化是类加载的最后一步，除了加载阶段，用户可以通过自定义的类加载器参与，其他阶段都完全由虚拟机主导和控制。到了初始化阶段才真正执行Java代码。
    
    类的初始化的主要工作是为静态变量赋程序设定的初值。
    
    如static int a = 100;在准备阶段，a被赋默认值0，在初始化阶段就会被赋值为100。

* 6、使用阶段
* 7、卸载阶段


初始化这个阶段，JVM虚拟机给出了5种必须对类进行“初始化”的情况



* 使用new关键字实例化对象的时候、读取或设置一个类的静态字段的时候、调用一个类的静态方法的时候；


* 使用java.lang.reflect包的方法对类进行反射调用的时候，如果类没有进行过初始化，则要先触发其初始化；


* 当初始化一个类的时候，如果发现其父类还没有被初始化，则要先初始化其父类；


* 当虚拟机启动时，用户需要指定一个执行的主类（包含main方法的那个类），则虚拟机会优先初始化这个主类；


* 在JDK1.7以后，动态语言支持的时候，如果一个java.lang.invoke.MethodHandle实例最后的结果是要执行第1种情况的操作，则也要进行初始化


注意，虚拟机规范使用了“有且只有”这个词描述，这五种情况被称为“主动引用”，除了这五种情况，所有其他的类引用方式都不会触发类初始化，被称为“被动引用”。



1、被动引用的例子一：

通过子类引用父类的静态字段，对于父类属于“主动引用”的第一种情况，对于子类，没有符合“主动引用”的情况，故子类不会进行初始化。代码如下：


    //父类
    public class SuperClass {
        //静态变量value
        public static int value = 666;
        //静态块，父类初始化时会调用
        static{
            System.out.println("父类初始化！");
        }
    }
     
    //子类
    public class SubClass extends SuperClass{
        //静态块，子类初始化时会调用
        static{
            System.out.println("子类初始化！");
        }
    }
     
    //主类、测试类
    public class NotInit {
        public static void main(String[] args){
            System.out.println(SubClass.value);
        }
    }
输出结果：

  父类初始化
  
  666
  
  

2、被动引用的例子之二：

通过数组来引用类，不会触发类的初始化，因为是数组new，而类没有被new，所以没有触发任何“主动引用”条款，属于“被动引用”。代码如下：


    //父类
    public class SuperClass {
        //静态变量value
        public static int value = 666;
        //静态块，父类初始化时会调用
        static{
            System.out.println("父类初始化！");
        }
    }
     
    //主类、测试类
    public class NotInit {
        public static void main(String[] args){
            SuperClass[] test = new SuperClass[10];
        }
    }
没有任何结果输出！

3、被动引用的例子之三：

刚刚讲解时也提到，静态常量在编译阶段就会被存入调用类的常量池中，不会引用到定义常量的类，这是一个特例，需要特别记忆，不会触发类的初始化！


    //常量类
    public class ConstClass {
        static{
            System.out.println("常量类初始化！");
        }
        
        public static final String HELLOWORLD = "hello world!";
    }
 
    //主类、测试类
    public class NotInit {
        public static void main(String[] args){
            System.out.println(ConstClass.HELLOWORLD);
        }
    }



类的卸载跟采用的垃圾收集算法有关，在CMS中有两种方法卸载不必要的类，
一种是等到元空间（Metaspace）满了的时候触发FGC，
另一种是使用跟CMS并发收集算法类似的方式，不过对于元空间的阈值和触发CMS并发收集的阈值
是独立的。更具体的可以参考之前的文章：CMS学习笔记。在这里，我们只需要记住，
JVM中一个类的卸载要满足下面这3个条件：

* 该类所有的实例对象都已被回收；


* 该类的类加载器对象已经被回收；


* 该类对应的java.lang.Class对象没有在任何地方被引用，无法在任何地方通过反射访问该类的方法。


###二、实例化过程
 new一个对象的过程：
 * 分配内存空间。(1)
 * 初始化对象。(2)
 * 将 singleton 对象指向分配的内存地址。(3)
 
 当产生指令重排在多线程情况下则会出现（3）在（2）之前执行，没有执行到（2）资源就被其他线程抢占。
 从而产生不完整对象。（例如：spring的循环依赖）
 
在Java中，一个对象在可以被使用之前必须要被正确地初始化，这一点是Java规范规定的。在实例化一个对象时，JVM首先会检查相关类型是否已经加载并初始化，如果没有，则JVM立即进行加载并调用类构造器完成类的初始化。在类初始化过程中或初始化完毕后，根据具体情况才会去对类进行实例化。

* 1、检查对象对应的类是否已被虚拟机加载

当虚拟机遇到new对应的字节码指令时，首先检查这个指令的参数是否能在常量池中找到一个类的符号引用，并检查该引用代表的类是否已被虚拟机加载、解析和初始化。如果没有则执行相应的类加载过程。

* 2、虚拟机为新生对象分配内存（对象所需的内存大小在类加载的过程中已经确定）

   内存分配有两种方式：

  * 🌳指针碰撞：假设Java堆中的内存是规整有序的。已用的内存聚集在一块，空闲的内存聚集在另一块。使用一个指针指向两块区域中间，那么需要分配的内存就仅仅把这个指针向空闲区域移动当前对象大小的距离。

  * 🌳空闲列表：如果Java堆内存是已用和空闲交错在一块，并且维护一个列表记录内存的使用情况。当需要分配一定大小的存储时，通过查询列表来获取存储空间。

  选择哪种分配方式由Java堆内存是否规整决定，而Java堆内存是否规整由取决于垃圾收集器是否带有压缩的功能。

* 3、线程安全问题

   由于对象的创建在虚拟机中是一个频繁的行为，可能会引起非线程安全问题。解决方法有两种：

  * 🌴对分配内存空间的动作进行同步处理——采用CAS（比较再交换）配上失败重试的方法保证更新操作的原子性

  * 🌴把内存分配的动作按照线程划分在不同的空间进行——每个线程预先分配一块内存（本地线程分配缓冲区TLAB），哪个线程要分配内存就在哪个TLAB中分配。当TLAB用完了，分配新的缓冲区时才需要同步锁定。

* 4、分配的内存空间初始化

  内存空间分配完成后，虚拟机必须对分配好的内存空间（不包括对象头）都进初始化为零值。这一步操作保证了对象的实例字段在Java代码中可以不用赋值直接使用。

* 5、设置对象头。

  将对象的所属类（即类的元数据信息）、对象的HashCode和对象的GC信息、锁信息等数据存放在对象的对象头中。

* 6、执行<init>()方法进行初始化。

  init()方法包含了初始化成员变量、执行实例化代码块和调用类的构造方法。

###三、对象的数据结构
* 1、对象头
  * （1） markword(与锁升级等有关)：
  
     第一部分markword,用于存储对象自身的运行时数据，如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程ID、偏向时间戳等，这部分数据的长度在32位和64位的虚拟机（未开启压缩指针）中分别为32bit和64bit，官方称它为“MarkWord”。
  * （2） klass：
     
     对象头的另外一部分是klass类型指针，即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例.
  * （3） 数组长度（只有数组对象有）：
     
     如果对象是一个数组, 那在对象头中还必须有一块数据用于记录数组长度.

* 2、实例数据
  * 实例数据部分是对象真正存储的有效信息，也是在程序代码中所定义的各种类型的字段内容。无论是从父类继承下来的，还是在子类中定义的，都需要记录起来。
* 3、对齐填充
  * 第三部分对齐填充并不是必然存在的，也没有特别的含义，它仅仅起着占位符的作用。由于HotSpot VM的自动内存管理系统要求对象起始地址必须是8字节的整数倍，换句话说，就是对象的大小必须是8字节的整数倍。而对象头部分正好是8字节的倍数（1倍或者2倍），因此，当对象实例数据部分没有对齐时，就需要通过对齐填充来补全。
* 注：对象大小的计算：
  * 1、 在32位系统下，存放Class指针的空间大小是4字节，MarkWord是4字节，对象头为8字节。
  * 2、 在64位系统下，存放Class指针的空间大小是8字节，MarkWord是8字节，对象头为16字节。
  * 3、 64位开启指针压缩的情况下，存放Class指针的空间大小是4字节，MarkWord是8字节，对象头为12字节。 数组长度4字节+数组对象头8字节(对象引用4字节（未开启指针压缩的64位为8字节）+数组markword为4字节（64位未开启指针压缩的为8字节）)+对齐4=16字节。
  * 4、 静态属性不算在对象大小内。
###四、访问对象的方式
* 1、句柄访问
* 2、直接指针访问

###五、类加载器

类加载器的作用:


类的加载是需要类加载器完成的，但是类加载器在JVM中的作用可不止这些。
在JVM中，一个类的唯一性是需要这个类本身和类加载器一起才能确定的，
每个类加载器都有一个独立的命名空间。
不同的类加载器，即使是同一个类字节码文件，最后再JVM里的类对象也不是同一个，
下面的代码展示了这个结论：



    package jvm;
    
    import java.io.IOException;
    import java.io.InputStream;
    
    public class ClassLoaderTest {
        public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
            ClassLoader myLoader = new ClassLoader() {
                @Override
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    try {
                        byte[] b = new byte[inputStream.available()];
                        inputStream.read(b);
                        return defineClass(name, b, 0, b.length);
                    } catch (IOException e) {
                        throw new ClassNotFoundException();
                    }
                }
            };
    
            Object obj = myLoader.loadClass("jvm.ClassLoaderTest").newInstance();
            System.out.println(obj.getClass());
            System.out.println(obj instanceof jvm.ClassLoaderTest);
    
            ClassLoaderTest classLoaderTest = new ClassLoaderTest();
            System.out.println(classLoaderTest.getClass());
            System.out.println(classLoaderTest instanceof jvm.ClassLoaderTest);
        }
    }


可以看出，代码中使用自定义类加载器（myLoader）
加载的jvm.ClassLoaderTest类和通过应用程序类加载器加载的类不是同一个类。
综上，类加载器在JVM中的作用有：

* 将类的字节码文件从JVM外部加载到内存中
* 确定一个类的唯一性
* 提供隔离特性，为中间件开发者提供便利，例如Tomcat




JVM中的类的加载器主要有三种:启动类加载器，拓展类加载器，应用类加载器。

* 1、启动类加载器(Bootstrap classLoader):又称为引导类加载器，由C++编写，无法通过程序得到。主要负责加载JAVA中的一些核心类库，主要是位于<JAVA_HOME>/lib/rt.jar中。

* 2、拓展类加载器(Extension classLoader):主要加载JAVA中的一些拓展类，位于<JAVA_HOME>/lib/ext中,是启动类加载器的子类。

* 3、应用类加载器(System classLoader):    又称为系统类加载器,主要用于加载CLASSPATH路径下我们自己写的类，是拓展类加载器的子类。

* 自定义类加载器（自己继承ClassLoader）


类加载器的三大特性:委托性、可见性、单一性

* 委托性:每个类中都有一个自己的类加载器的属性，这也就是为什么可以通过Student.class.getClassLoader()来    获取自己的类加载器。当一个类加载器要加载一个类时，它会先委托自己的父类加载器来加载，只有当父加载器无法加载类时，才会自己去加载。例如我们写了一个类Student,它的类加载器是System ClassLoader,它首先会委托给它的父加载器即Extension ClassLoader,然后Extension ClassLoader又会委托给它的父加载器BootStrap ClassLoader,启动类加载器无法加载这个类，交给拓展类加载器，拓展类加载器也无法加载，然后才轮到系统类加载器进行加载。

* 可见性：可见性指的是父加载器无法利用子加载器加载的类，而子加载器可以利用父加载器加载的类。

* 单一性:一个类只会被一个类加载器加载一次，不会被重复加载。

###六、双亲委派机制
* 双亲委派机制说简单点就是，先找父亲加载，不行再由儿子自己加载

* 为什么要设计双亲委派机制？
  * 沙箱安全机制：自己写的java.lang.String.class类不会被加载，这样便可以防止核心API库被随意篡改
  * 避免类的重复加载：当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次，保证被加载类的唯一性 看一个类加载示例：
    
    
    package java.lang;
    
    public class String {
        public static void main(String[] args) {
            System.out.println("**************My String Class**************");
        }
    }
    
    运行结果：
    错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
       public static void main(String[] args)
    否则 JavaFX 应用程序类必须扩展javafx.application.Application


* 打破双亲委派机制:

  再来一个沙箱安全机制示例，尝试打破双亲委派机制，用自定义类加载器加载我们自己实现的 java.lang.String.class
      
      
      public class MyClassLoaderTest2 {
          static class MyClassLoader extends ClassLoader {
              private String classPath;
      
              public MyClassLoader(String classPath) {
                  this.classPath = classPath;
              }
      
              private byte[] loadByte(String name) throws Exception {
                  name = name.replaceAll("\\.", "/");
                  FileInputStream fis = new FileInputStream(classPath + "/" + name
                          + ".class");
                  int len = fis.available();
                  byte[] data = new byte[len];
                  fis.read(data);
                  fis.close();
                  return data;
      
              }
      
              protected Class<?> findClass(String name) throws ClassNotFoundException {
                  try {
                      byte[] data = loadByte(name);
                      return defineClass(name, data, 0, data.length);
                  } catch (Exception e) {
                      e.printStackTrace();
                      throw new ClassNotFoundException();
                  }
              }
      
              /**
               * 重写类加载方法，实现自己的加载逻辑，不委派给双亲加载
               *
               * @param name
               * @param resolve
               * @return
               * @throws ClassNotFoundException
               */
              @Override
              protected Class<?> loadClass(String name, boolean resolve)
                      throws ClassNotFoundException {
                  synchronized (getClassLoadingLock(name)) {
                      // First, check if the class has already been loaded
                      Class<?> c = findLoadedClass(name);
      
                      if (c == null) {
                          // If still not found, then invoke findClass in order
                          // to find the class.
                          long t1 = System.nanoTime();
                          c = findClass(name);
      
                          // this is the defining class loader; record the stats
                          sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                          sun.misc.PerfCounter.getFindClasses().increment();
                      }
                      if (resolve) {
                          resolveClass(c);
                      }
                      return c;
                  }
              }
          }
      
          public static void main(String args[]) throws Exception {
              MyClassLoader classLoader = new MyClassLoader("D:/test");
              //尝试用自己改写类加载机制去加载自己写的java.lang.String.class
              Class clazz = classLoader.loadClass("java.lang.String");
              Object obj = clazz.newInstance();
              Method method = clazz.getDeclaredMethod("sout", null);
              method.invoke(obj, null);
              System.out.println(clazz.getClassLoader().getClass().getName());
          }
      }
      
      运行结果：
      java.lang.SecurityException: Prohibited package name: java.lang
          at java.lang.ClassLoader.preDefineClass(ClassLoader.java:659)
          at java.lang.ClassLoader.defineClass(ClassLoader.java:758)
     