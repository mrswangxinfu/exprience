代理模式（Proxy）

效果
1. 通过代理，控制对对象的访问。

2. 可以详细控制访问某个（某类）对象的方法，在调用方法前做前置处理，调用方法后做后置处理（即APO的微观实现）。

3. AOP（Aspect Oriented Programming，面向切面编程）的核心实现机制。




核心角色
1. 抽象角色：定义代理角色和真实角色的公共对外方法。

2. 真实角色：实现抽象角色，定义真实角色所要实现的业务逻辑，供代理角色调用。

3. 代理角色：实现抽象角色，是真实角色的代理，通过真实角色的业务逻辑方法来实现抽象方法，并可以附加自己操作。


分类
1. 静态代理（静态定义代理类）。

2. 动态代理（动态定义代理类）：

    a) JDK自带的动态代理（java.lang.reflect.Proxy动态生成代理类和对象，java.lang.reflect.InvocationHandler通过invoke()方法实现访问真实角色）；

    b) javaassist字节码操作库实现；

    c) CGLIB；

    d) ASM（底层使用指令，可维护性较差）。


代码（静态代理）


   1. Star.java


     1 public interface Star {
     2 
     3     void confer();
     4     
     5     void signContract();
     6     
     7     void bookTicket();
     8     
     9     void sing();
    10     
    11     void collectMoney();
    12     
    13 }


   2. RealStar.java

     1 public class RealStar implements Star {
     2 
     3     @Override
     4     public void confer() {
     5         System.out.println("RealStar.confer()");
     6     }
     7 
     8     @Override
     9     public void signContract() {
    10         System.out.println("RealStar.signContract()");
    11     }
    12 
    13     @Override
    14     public void bookTicket() {
    15         System.out.println("RealStar.bookTicket()");
    16     }
    17 
    18     @Override
    19     public void sing() {
    20         System.out.println("RealStar.sing()");
    21     }
    22 
    23     @Override
    24     public void collectMoney() {
    25         System.out.println("RealStar.collectMoney()");
    26     }
    27 
    28 }


   3. ProxyStar.java


     1 public class ProxyStar implements Star {
     2     
     3     private Star realStar;
     4     
     5     public ProxyStar(Star realStar) {
     6         this.realStar = realStar;
     7     }
     8 
     9     @Override
    10     public void confer() {
    11         System.out.println("ProxyStar.confer()");
    12     }
    13 
    14     @Override
    15     public void signContract() {
    16         System.out.println("ProxyStar.signContract()");
    17     }
    18 
    19     @Override
    20     public void bookTicket() {
    21         System.out.println("ProxyStar.bookTicket()");
    22     }
    23 
    24     @Override
    25     public void sing() {
    26         realStar.sing();
    27     }
    28 
    29     @Override
    30     public void collectMoney() {
    31         System.out.println("ProxyStar.collectMoney()");
    32     }
    33 
    34 }


   4. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         RealStar realStar = new RealStar();
     5         ProxyStar proxyStar = new ProxyStar(realStar);
     6         proxyStar.confer();
     7         proxyStar.signContract();
     8         proxyStar.bookTicket();
     9         proxyStar.sing();
    10         proxyStar.collectMoney();
    11     }
    12 
    13 }


  代码（动态代理）
 
   1. Star.java

     1 public interface Star {
     2 
     3     void confer();
     4     
     5     void signContract();
     6     
     7     void bookTicket();
     8     
     9     void sing();
    10     
    11     void collectMoney();
    12     
    13 }


   2. RealStar.java

     1 public class RealStar implements Star {
     2 
     3     @Override
     4     public void confer() {
     5         System.out.println("RealStar.confer()");
     6     }
     7 
     8     @Override
     9     public void signContract() {
    10         System.out.println("RealStar.signContract()");
    11     }
    12 
    13     @Override
    14     public void bookTicket() {
    15         System.out.println("RealStar.bookTicket()");
    16     }
    17 
    18     @Override
    19     public void sing() {
    20         System.out.println("RealStar.sing()");
    21     }
    22 
    23     @Override
    24     public void collectMoney() {
    25         System.out.println("RealStar.collectMoney()");
    26     }
    27 
    28 }

   3. StarHandler.java

     1 import java.lang.reflect.InvocationHandler;
     2 import java.lang.reflect.Method;
     3 
     4 public class StarHandler implements InvocationHandler {
     5     
     6     private Star realStar;
     7 
     8     public StarHandler(Star realStar) {
     9         this.realStar = realStar;
    10     }
    11 
    12     @Override
    13     public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    14         switch (method.getName()) {
    15         case "confer":
    16             System.out.println("ProxyStar.confer()");
    17             break;
    18         case "signContract":
    19             System.out.println("ProxyStar.signContract()");
    20             break;
    21         case "bookTicket":
    22             System.out.println("ProxyStar.bookTicket()");
    23             break;
    24         case "sing":
    25             method.invoke(realStar, args);
    26             break;
    27         case "collectMoney":
    28             System.out.println("ProxyStar.collectMoney()");
    29             break;
    30         }
    31         
    32         return null;
    33     }
    34 
    35 }

   4. Client.java

     1 import java.lang.reflect.Proxy;
     2 
     3 public class Client {
     4 
     5     public static void main(String[] args) {
     6         Star realStar = new RealStar();
     7         StarHandler handler = new StarHandler(realStar);
     8         Star proxyStar = (Star) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] { Star.class }, handler);
     9         proxyStar.confer();
    10         proxyStar.signContract();
    11         proxyStar.bookTicket();
    12         proxyStar.sing();
    13         proxyStar.collectMoney();
    14     }
    15 
    16 }


应用场景
1. 安全代理：屏蔽对真实角色的直接访问。

2. 远程代理：通过代理类处理远程方法调用（RMI）。

3. 延迟代理：先加载轻量级代理对象，真正需要时再加载真实对象