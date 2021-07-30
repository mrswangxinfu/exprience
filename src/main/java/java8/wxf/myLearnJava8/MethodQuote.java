package java8.wxf.myLearnJava8;

import java8.wxf.entity.Person;
import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用：是在lambda表达式基础上修改为方法引用的，即函数式接口可以引用其他对象的构造器、静态方法等作为其抽象方法的具体实现
 *  注意：1、引用的方法返回值要与抽象方法一致
 *       2、实例对象使用双冒号去引用方法则被引用的方法不能是静态方法
 * 符号是双冒号
 */
public class MethodQuote {

    @Test
    public void test1() {
        Supplier<Person> supplier=()-> new Person();
        //方法引用的构造器引用,即引用被引用对象的构造器作为抽象方法的实现
        Supplier<Person> supplier1=Person::new;
        supplier1.get();

        Supplier<Student> supplier12 = Student::new;
        supplier12.get();
        //有参构造器引用
        Function<String,Person> function1=s->{return new Person(s);};
        function1.apply("+++");// 走构造器
        Function<String,Person> function=Person::new;
        function.apply("constructor");

    }

    @Test
    public void test2(){
        //静态方法引用
        Person.print("person static","123");

        BiConsumer<String,String> biConsumer=(s1,s2)->Person.print(s1,s2);
        biConsumer.accept("person to ","BiConsumer");

        //方法引用
        WriterOut<String> writerOut =(s)->Person.print1(s);

        writerOut.print("hello");

        BiConsumer<String,String> consumer = Person::print;

        consumer.accept("oo", "kk");
    }

    @Test
    public void test3(){
        //实例方法引用
        Person person=new Person();
        person.print("hello","word");

        Consumer<String> consumer=s->person.print(s);
        consumer.accept("hello");

        Consumer<String> consumer1=person::print;
        consumer1.accept("world");
    }
    @FunctionalInterface
    interface WriterOut<T> {
        void print(T t);
    }

    class Student<T> {
        private int age;

        public Student() {
            System.out.println("student");
        }

        T play(T t) {
            return t;
        }
    }

}
