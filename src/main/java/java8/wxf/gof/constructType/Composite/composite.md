组合模式（Composite）

效果
1. 把部分和整体的关系用树形结构表示，从而使客户端可以使用统一方式处理部分对象和整体对象。

2. 组合模式为处理树形结构提供了完美的解决方案，描述了如何将容器和叶子进行递归组合，使得用户在使用时可以一致性的对待容器和叶子。


核心角色
1. 抽象构件角色（Component）：定义了叶子和容器构件的共同点。

2. 叶子构件角色（Leaf）：无子节点。

3. 容器构件角色（Composite）：有容器特征，可以包含子节点。


代码


   Component.java

     1 import java.util.List;
     2 
     3 public interface Component {
     4 
     5     void operation();
     6     
     7 }
     8 
     9 interface Leaf extends Component {
    10     
    11 }
    12 
    13 interface Composite extends Component {
    14     
    15     void add(Component child);
    16     
    17     void remove(Component child);
    18     
    19     List<Component> getChildren();
    20     
    21 }


  代码（杀毒举例）

   1. AbstractFile.java

     1 import java.util.ArrayList;
     2 import java.util.List;
     3 
     4 public interface AbstractFile {
     5 
     6     void killVirus();
     7     
     8 }
     9 
    10 class File implements AbstractFile {
    11     
    12     private String name;
    13 
    14     public File(String name) {
    15         this.name = name;
    16     }
    17 
    18     @Override
    19     public void killVirus() {
    20         System.out.println(name + " 文件杀毒！");
    21     }
    22     
    23 }
    24 
    25 class Folder implements AbstractFile {
    26     
    27     private String name;
    28     private List<AbstractFile> children = new ArrayList<>();
    29 
    30     public Folder(String name) {
    31         this.name = name;
    32     }
    33     
    34     public void add(AbstractFile child) {
    35         children.add(child);
    36     }
    37     
    38     public void remove(AbstractFile child) {
    39         children.remove(child);
    40     }
    41 
    42     @Override
    43     public void killVirus() {
    44         for (AbstractFile child : children) {
    45             child.killVirus();
    46         }
    47         System.out.println(name + " 文件夹杀毒！");
    48     }
    49     
    50 }


   2. Client.java

     1 public class Client {
     2 
     3     public static void main(String[] args) {
     4         Folder myDocument = new Folder("我的文档");
     5         myDocument.add(new File("Client.java"));
     6         myDocument.add(new File("index.php"));
     7         myDocument.add(new File("老师.avi"));
     8         Folder book = new Folder("图书");
     9         book.add(new File("设计模式.pdf"));
    10         book.add(new File("Hadoop权威指南.pdf"));
    11         myDocument.add(book);
    12         myDocument.killVirus();
    13     }
    14 
    15 }


应用场景
1. 操作系统的资源管理器。

2. GUI的容器层次图。

3. XML文件解析。

4. OA系统的组织结构处理。

5. JUnit单元测试框架：Test接口（抽象）、TestCase（叶子）、TestUnit（容器）。