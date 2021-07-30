观察者模式（Observer）

效果
1. 观察者模式用于1:N的消息通知。

2. 当目标对象（Subject或Observable）的状态变化（消息发布）时，他及时告知一系列观察者对象（Observer），令他们做出相应（消息订阅）。

3. 通知观察者的方式：

    a) 推：每次都会把消息以广播方式发送给所有观察者，所有观察者只能被动接收。

    b) 拉：观察者只要知道有变化即可，什么时候获取消息、获取什么内容，都由观察者自主决定。




代码


   1. Subject.java

     1 import java.util.ArrayList;
     2 import java.util.List;
     3 
     4 public abstract class Subject {
     5 
     6     private List<Observer> observers = new ArrayList<>();
     7     
     8     public void subscribe(Observer observer) {
     9         observers.add(observer);
    10     }
    11     
    12     public void unsubscribe(Observer observer) {
    13         observers.remove(observer);
    14     }
    15     
    16     public void notifyAllObservers() {
    17         for (Observer observer : observers) {
    18             observer.update(this);
    19         }
    20     }
    21     
    22 }



   2. ConcreteSubject.java

     1 public class ConcreteSubject extends Subject {
     2 
     3     private int state;
     4 
     5     public int getState() {
     6         return state;
     7     }
     8 
     9     public void setState(int state) {
    10         this.state = state;
    11         notifyAllObservers();
    12     }
    13     
    14 }



   3. Observer.java

    1 public interface Observer {
    2 
    3     void update(Subject subject);
    4     
    5 }


   4. ConcreteObserver.java

     1 public class ConcreteObserver implements Observer {
     2     
     3     private String name;
     4 
     5     public ConcreteObserver(String name) {
     6         this.name = name;
     7     }
     8 
     9     @Override
    10     public void update(Subject subject) {
    11         System.out.println(name + "收到消息：state=" + ((ConcreteSubject) subject).getState());
    12     }
    13 
    14 }


   5. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         ConcreteSubject subject = new ConcreteSubject();
     5         Observer observer1 = new ConcreteObserver("张三");
     6         Observer observer2 = new ConcreteObserver("李四");
     7         Observer observer3 = new ConcreteObserver("王五");
     8         subject.subscribe(observer1);
     9         subject.subscribe(observer2);
    10         subject.subscribe(observer3);
    11         
    12         subject.setState(1);
    13         subject.setState(2);
    14     }
    15 
    16 }


  代码（基于JDK）
  
   1. ConcreteSubject.java

     1 import java.util.Observable;
     2 
     3 public class ConcreteSubject extends Observable {
     4 
     5     private int state;
     6 
     7     public int getState() {
     8         return state;
     9     }
    10 
    11     public void setState(int state) {
    12         this.state = state;
    13         // 目标对象已变化
    14         setChanged();
    15         // 通知观察者
    16         notifyObservers(state);
    17     }
    18     
    19 }


   2. ConcreteObserver.java

     1 import java.util.Observable;
     2 import java.util.Observer;
     3 
     4 public class ConcreteObserver implements Observer {
     5 
     6     private String name;
     7 
     8     public ConcreteObserver(String name) {
     9         this.name = name;
    10     }
    11     
    12     @Override
    13     public void update(Observable observable, Object arg) {
    14         ConcreteSubject subject = (ConcreteSubject) observable;
    15         System.out.println(name + "收到消息：" + arg);
    16         System.out.println(name + "获取最新状态：" + subject.getState());
    17     }
    18 
    19 }


   3. Client.java

     1 import java.util.Observer;
     2 
     3 public class Client {
     4 
     5     public static void main(String[] args) {
     6         ConcreteSubject subject = new ConcreteSubject();
     7         Observer observer1 = new ConcreteObserver("张三");
     8         Observer observer2 = new ConcreteObserver("李四");
     9         Observer observer3 = new ConcreteObserver("王五");
    10         subject.addObserver(observer1);
    11         subject.addObserver(observer2);
    12         subject.addObserver(observer3);
    13         
    14         subject.setState(1);
    15         subject.setState(2);
    16     }
    17 
    18 }


应用场景
1. 聊天室，服务器转发给所有客户端。

2. 网络游戏多人联机对战，服务器将客户端的状态进行分发。

3. 邮件订阅。

4. Servlet编程，监听器的实现。

5. Android，广播机制。

6. 京东商城，群发某商品打折信息。