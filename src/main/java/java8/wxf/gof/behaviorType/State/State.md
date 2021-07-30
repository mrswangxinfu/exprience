状态模式（State）

效果
1. 用于解决系统中复杂对象的状态转换以及不同状态下行为的封装问题。

2. 酒店系统中，房间的状态变化。




核心角色
1. 上下文类（Context）：环境类中维护一个State对象，定义了当前的状态。

2. 抽象状态类（State）。

3. 具体状态类（ConcreteState）：每一个类封装了一个状态对应的行为。


代码

   1. State.java

    1 public interface State {
    2 
    3     void handle();
    4     
    5 }
   2. RoomContext.java

     1 public class RoomContext {
     2 
     3     private State state;
     4 
     5     public State getState() {
     6         return state;
     7     }
     8 
     9     public void setState(State state) {
    10         this.state = state;
    11         state.handle();
    12     }
    13     
    14 }


   3. FreeState.java

    1 public class FreeState implements State {
    2 
    3     @Override
    4     public void handle() {
    5         System.out.println("退出房间！");
    6     }
    7 
    8 }


   4. BookedState.java

    1 public class BookedState implements State {
    2 
    3     @Override
    4     public void handle() {
    5         System.out.println("预定房间！");
    6     }
    7 
    8 }


   5. CheckedInState.java

    1 public class CheckedInState implements State {
    2 
    3     @Override
    4     public void handle() {
    5         System.out.println("入住房间！");
    6     }
    7 
    8 }


   6. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         RoomContext context = new RoomContext();
     5         context.setState(new FreeState());
     6         context.setState(new BookedState());
     7         context.setState(new CheckedInState());
     8     }
     9 
    10 }


应用场景
1. 银行系统的账号状态管理。

2. OA系统的公文状态管理。

3. 酒店系统的房间状态管理。

4. 线程对象各状态之间的切换。