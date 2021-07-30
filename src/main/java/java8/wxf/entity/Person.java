package java8.wxf.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Person {
    private String id;
    private String name;
    private String age;
    public Person(){
        System.out.println("person====");
    }
    public Person(String message){
        System.out.println(message);
    }
    public static void print(String s,String b){
        System.out.println(s+b);
    }
    public void print(String s){
        System.out.println(s);
    }
    public static void print1(String s){
        System.out.println(s);
    }
    /** 使用 <T> 将方法声明为泛型方法*/
    public <T> T get(T value){
        return value;
    }

}
