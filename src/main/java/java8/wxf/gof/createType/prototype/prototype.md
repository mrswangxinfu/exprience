原型模式（Prototype）

效果
1. 通过new创建对象需要非常繁琐的数据准备或访问权限，则可以使用原型模式。

2. 就是Java中的克隆技术，以某个对象为原型，复制出新的对象。

3. 优势：效率高，避免重新执行构建过程。

4. 克隆类似于new，但不同于new。new创建新的对象属性采用默认值。克隆出的对象属性完全与原型对象相同，并且克隆出的新对象改变不会影响原型对象，然后再修改克隆对象的值。


核心角色
1. Clonable接口的clone()方法。

2. 实现原型模式最困难的是内存复制操作，所幸Java提供了clone()方法。


代码（浅克隆）
   1. Sheep.java

     1 import java.util.Date;
     2 
     3 public class Sheep implements Cloneable {
     4 
     5     private String name;
     6     
     7     private Date birthday;
     8     
     9     @Override
    10     protected Object clone() throws CloneNotSupportedException {
    11         return super.clone();
    12     }
    13 
    14     public String getName() {
    15         return name;
    16     }
    17 
    18     public void setName(String name) {
    19         this.name = name;
    20     }
    21 
    22     public Date getBirthday() {
    23         return birthday;
    24     }
    25 
    26     public void setBirthday(Date birthday) {
    27         this.birthday = birthday;
    28     }
    29     
    30 }


   2. Client.java

     1 import java.util.Date;
     2 
     3 public class Client {
     4 
     5     public static void main(String[] args) throws Exception {
     6         Sheep sheep1 = new Sheep();
     7         sheep1.setName("少利");
     8         sheep1.setBirthday(new Date());
     9         
    10         Sheep sheep2 = (Sheep) sheep1.clone();
    11         sheep2.setName("多利");
    12         System.out.println(sheep2.getName());
    13         System.out.println(sheep2.getBirthday());
    14         
    15         // 浅克隆
    16         System.out.println(sheep1.getBirthday() == sheep2.getBirthday());
    17     }
    18 
    19 }


  代码（基于JDK的深克隆）
   1. Sheep.java

     1 import java.util.Date;
     2 
     3 public class Sheep implements Cloneable {
     4 
     5     private String name;
     6     
     7     private Date birthday;
     8     
     9     @Override
    10     protected Object clone() throws CloneNotSupportedException {
    11         Sheep sheep = (Sheep) super.clone();
    12         sheep.setBirthday((Date) birthday.clone());
    13         return sheep;
    14     }
    15 
    16     public String getName() {
    17         return name;
    18     }
    19 
    20     public void setName(String name) {
    21         this.name = name;
    22     }
    23 
    24     public Date getBirthday() {
    25         return birthday;
    26     }
    27 
    28     public void setBirthday(Date birthday) {
    29         this.birthday = birthday;
    30     }
    31     
    32 }


   2. Client.java


     1 import java.util.Date;
     2 
     3 public class Client {
     4 
     5     public static void main(String[] args) throws Exception {
     6         Sheep sheep1 = new Sheep();
     7         sheep1.setName("少利");
     8         sheep1.setBirthday(new Date());
     9         
    10         Sheep sheep2 = (Sheep) sheep1.clone();
    11         sheep2.setName("多利");
    12         System.out.println(sheep2.getName());
    13         System.out.println(sheep2.getBirthday());
    14         
    15         // 深克隆
    16         System.out.println(sheep1.getBirthday() == sheep2.getBirthday());
    17     }
    18 
    19 }


  代码（基于序列化的深克隆）
   1. Sheep.java

     1 import java.io.ByteArrayInputStream;
     2 import java.io.ByteArrayOutputStream;
     3 import java.io.IOException;
     4 import java.io.ObjectInputStream;
     5 import java.io.ObjectOutputStream;
     6 import java.io.Serializable;
     7 import java.util.Date;
     8 
     9 public class Sheep implements Cloneable, Serializable {
    10 
    11     private static final long serialVersionUID = 2155997264135266066L;
    12 
    13     private String name;
    14     
    15     private Date birthday;
    16     
    17     @Override
    18     protected Object clone() throws CloneNotSupportedException {
    19         Sheep sheep = null;
    20         ByteArrayOutputStream baos = null;
    21         ObjectOutputStream oos = null;
    22         ByteArrayInputStream bais = null;
    23         ObjectInputStream ois = null;
    24         try {
    25             baos = new ByteArrayOutputStream();
    26             oos = new ObjectOutputStream(baos);
    27             oos.writeObject(this);
    28             byte[] bytes = baos.toByteArray();
    29             bais = new ByteArrayInputStream(bytes);
    30             ois = new ObjectInputStream(bais);
    31             sheep = (Sheep) ois.readObject();
    32             
    33         } catch (IOException | ClassNotFoundException e) {
    34             e.printStackTrace();
    35         }
    36         return sheep;
    37     }
    38 
    39     public String getName() {
    40         return name;
    41     }
    42 
    43     public void setName(String name) {
    44         this.name = name;
    45     }
    46 
    47     public Date getBirthday() {
    48         return birthday;
    49     }
    50 
    51     public void setBirthday(Date birthday) {
    52         this.birthday = birthday;
    53     }
    54     
    55 }


   2. Client.java


     1 import java.util.Date;
     2 
     3 public class Client {
     4 
     5     public static void main(String[] args) throws Exception {
     6         Sheep sheep1 = new Sheep();
     7         sheep1.setName("少利");
     8         sheep1.setBirthday(new Date());
     9         
    10         Sheep sheep2 = (Sheep) sheep1.clone();
    11         sheep2.setName("多利");
    12         System.out.println(sheep2.getName());
    13         System.out.println(sheep2.getBirthday());
    14         
    15         // 深克隆
    16         System.out.println(sheep1.getBirthday() == sheep2.getBirthday());
    17     }
    18 
    19 }


应用场景
1. 原型模式很少单独使用，一般与工厂模式一起出现。通过clone()方法创建对象后由工厂模式返回。

2. Spring的Bean创建有单例模式和原型模式两种方式。