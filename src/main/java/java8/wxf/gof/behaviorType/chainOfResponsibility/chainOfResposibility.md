责任链模式（Chain of Resposibility）
  
  说白就是链表的实现过程
  
效果
1. 将能够处理同一类请求的对象连成一条链，所提交的请求沿着链传递，链上的对象逐个判断是否有能力处理该请求，如果能则处理，否则传递给链上的下一个对象。

2. 请假条审批过程：天数小于3天，主任审批；大于等于3天，小于10天，经理审批；大于等于10天，小于30天，总经理审批；大于等于30天，拒绝。




核心角色
1. 责任链既可以通过LinkedList实现，也可以通过ArrayList实现。

2. 可事先定义好责任链存储到配置文件或数据库。


代码
   1. LeaveRequest.java


     1 public class LeaveRequest {
     2 
     3     private String name;
     4     
     5     private int leaveDays;
     6     
     7     private String reason;
     8 
     9     public LeaveRequest(String name, int leaveDays, String reason) {
    10         this.name = name;
    11         this.leaveDays = leaveDays;
    12         this.reason = reason;
    13     }
    14 
    15     public String getName() {
    16         return name;
    17     }
    18 
    19     public int getLeaveDays() {
    20         return leaveDays;
    21     }
    22 
    23     public String getReason() {
    24         return reason;
    25     }
    26 
    27 }



   2. Leave.java

     1 public abstract class Leader {
     2 
     3     protected String name;
     4     
     5     protected Leader nextLeader;
     6 
     7     public Leader(String name) {
     8         this.name = name;
     9     }
    10 
    11     public void setNextLeader(Leader nextLeader) {
    12         this.nextLeader = nextLeader;
    13     }
    14     
    15     public abstract void handleRequest(LeaveRequest leaveRequest);
    16     
    17 }
    18 
    19 class Director extends Leader {
    20 
    21     public Director(String name) {
    22         super(name);
    23     }
    24 
    25     @Override
    26     public void handleRequest(LeaveRequest leaveRequest) {
    27         if (leaveRequest.getLeaveDays() < 3) {
    28             System.out.println("主任" + name + "审批通过！");
    29         } else if (nextLeader != null) {
    30             nextLeader.handleRequest(leaveRequest);
    31         }
    32     }
    33     
    34 }
    35 
    36 class Manager extends Leader {
    37     
    38     public Manager(String name) {
    39         super(name);
    40     }
    41     
    42     @Override
    43     public void handleRequest(LeaveRequest leaveRequest) {
    44         if (leaveRequest.getLeaveDays() < 10) {
    45             System.out.println("经理" + name + "审批通过！");
    46         } else if (nextLeader != null) {
    47             nextLeader.handleRequest(leaveRequest);
    48         }
    49     }
    50     
    51 }
    52 
    53 class GeneralManager extends Leader {
    54 
    55     public GeneralManager(String name) {
    56         super(name);
    57     }
    58 
    59     @Override
    60     public void handleRequest(LeaveRequest leaveRequest) {
    61         if (leaveRequest.getLeaveDays() < 30) {
    62             System.out.println("总经理" + name + "审批通过！");
    63         } else {
    64             System.out.println("不通过！");
    65         }
    66     }
    67     
    68 }


   3. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         Leader director = new Director("张三");
     5         Leader manager = new Manager("李四");
     6         Leader generalManager = new GeneralManager("王五");
     7         director.setNextLeader(manager);
     8         manager.setNextLeader(generalManager);
     9         
    10         LeaveRequest leaveRequest = new LeaveRequest("Tom", 5, "回英国老家。");
    11         director.handleRequest(leaveRequest);
    12     }
    13 
    14 }


应用场景
1. Java的异常捕获，try可对应多个catch，当第一个catch不匹配，则自动跳到第二个catch。

2. JavaScript的事件冒泡和捕获机制。

3. Servlet开发中，过滤器的链式处理