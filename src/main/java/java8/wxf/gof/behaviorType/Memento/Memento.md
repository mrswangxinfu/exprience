备忘录模式（Memento）

效果：

保存某个对象内部状态的拷贝，以后可以将该对象恢复到原先状态。


核心角色
1. 源发器类（Originator）：负责创建一个备忘录类，用以记录当前内部状态，并可使用备忘录恢复内部状态。

2. 备忘录类（Memento）：负责存储源发器类的内部状态，并可防止源发器类以外的其他对象访问备忘录类。

3. 负责人类（CareTaker）：负责保存好备忘录，备忘点较多时，可用List或Stack存储。也可以持久化。


  代码

   1. Employee.java

     1 // 源发器类
     2 public class Employee {
     3 
     4     private String name;
     5     
     6     private int age;
     7     
     8     private double salary;
     9 
    10     public Employee(String name, int age, double salary) {
    11         this.name = name;
    12         this.age = age;
    13         this.salary = salary;
    14     }
    15     
    16     // 备忘
    17     public EmployeeMemento memento() {
    18         return new EmployeeMemento(this);
    19     }
    20     
    21     // 恢复
    22     public void recover(EmployeeMemento employeeMemento) {
    23         this.name = employeeMemento.getName();
    24         this.age = employeeMemento.getAge();
    25         this.salary = employeeMemento.getSalary();
    26     }
    27 
    28     public String getName() {
    29         return name;
    30     }
    31 
    32     public void setName(String name) {
    33         this.name = name;
    34     }
    35 
    36     public int getAge() {
    37         return age;
    38     }
    39 
    40     public void setAge(int age) {
    41         this.age = age;
    42     }
    43 
    44     public double getSalary() {
    45         return salary;
    46     }
    47 
    48     public void setSalary(double salary) {
    49         this.salary = salary;
    50     }
    51     
    52     @Override
    53     public String toString() {
    54         return "name=" + name + ",age=" + age + ",salary=" + salary;
    55     }
    56     
    57 }


   2. EmployeeMemento.java

     1 public class EmployeeMemento {
     2 
     3     private String name;
     4 
     5     private int age;
     6 
     7     private double salary;
     8 
     9     public EmployeeMemento(Employee employee) {
    10         this.name = employee.getName();
    11         this.age = employee.getAge();
    12         this.salary = employee.getSalary();
    13     }
    14 
    15     public String getName() {
    16         return name;
    17     }
    18 
    19     public int getAge() {
    20         return age;
    21     }
    22 
    23     public double getSalary() {
    24         return salary;
    25     }
    26     
    27 }


   3. EmployeeCareTaker.java

     1 import java.util.ArrayList;
     2 import java.util.List;
     3 
     4 public class EmployeeCareTaker {
     5 
     6     private List<EmployeeMemento> mementoes = new ArrayList<>();
     7     
     8     public void addMemento(EmployeeMemento mementoe) {
     9         mementoes.add(mementoe);
    10     }
    11     
    12     public EmployeeMemento getMemento(int index) {
    13         return mementoes.get(index);
    14     }
    15     
    16     public EmployeeMemento getLastMemento() {
    17         return getMemento(mementoes.size() - 1);
    18     }
    19     
    20 }


   4. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         EmployeeCareTaker careTaker = new EmployeeCareTaker();
     5         
     6         Employee employee = new Employee("张三", 18, 1000);
     7         System.out.println(employee);
     8         
     9         careTaker.addMemento(employee.memento());
    10         employee.setAge(20);
    11         employee.setSalary(3000);
    12         System.out.println(employee);
    13         careTaker.addMemento(employee.memento());
    14         
    15         employee.setAge(21);
    16         System.out.println(employee);
    17         
    18         employee.recover(careTaker.getLastMemento());
    19         System.out.println(employee);
    20     }
    21 
    22 }


应用场景
1. 棋类游戏的悔棋。

2. 编辑软件的撤销操作。

3. 数据库的事务回滚操作。

4. Photoshop的历史版本记录。