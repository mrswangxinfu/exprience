package java8.wxf.myLearnJava8;

import java8.wxf.entity.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * 使用lambda表达式必须满足 接口有且只有一个抽象方法
 * lambda表达式可以取代匿名内部类，可以直接实现接口
 *
 *
 * 常见函数式接口：
 *  Predicate<T> boolean test(T t);
 *  Consumer<T> void accept(T t);
 *  Function<T,R> R apply(T t);
 *  Supplier<T> T get();
 *
 *  为减少基本数据类型装箱拆箱的开销，提供对应的函数式接口：
 *  例如：int
 *  IntPredicate boolean test(int t);
 *  IntConsumer void accept(int t);
 *  IntFunction<R> R apply(int t);
 *  IntSupplier int get();
 */
public class Lambda {

    public List<Person> getPeople() {
        List<Person> people=new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
           Person person=new Person(i+"","person"+ UUID.randomUUID()
                   .toString().substring(0,4),""+i*10);
           people.add(person);
        }
        return people;
    }
    @Test
    public void test1(){
        List<Person> people=getPeople();
        System.out.println(people);
        List<Person> people1=people.stream()
                .filter(person->new Integer(person.getAge())>50
                        &&new Integer(person.getAge())<80)
                .collect(Collectors.toList());
        System.out.println(people1);
        Comparator<Integer> comparator=(Integer a, Integer b)-> {return a>b?1:0;};
        System.out.println(comparator.compare(12,18));
    }
    @Test
    public void test2(){
        Comparator comparator1 = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        // 若重写Object方法就则不会认为Object的方法也是抽象的
        Compare<Integer> compare = (value1, value2) -> {
            return value1+value2;
        };

        System.out.println("====" + compare.equals(compare)); ;
        /**
         *lambda表达式
         */
        Function<Integer> function=(a,b)->{
            return a*b;
        };
        /**
         * 匿名内部类，匿名内部类不能改变外部变量，只能访问
         */
        Function<Integer> function1=new Function<Integer>() {
            @Override
            public int multiplication(Integer a, Integer b) {
                return a*b+100;
            }
        };
        System.out.println(function.multiplication(12,56));
        System.out.println(function1.multiplication(12,56));
        function.Sys();
    }
    @Test
    public void test3(){
        Predicate<Integer> predicate=a-> {return a>0?true:false;};
        System.out.println(predicate.test(12));

        Consumer<String> consumer=a-> System.out.println(a);
        consumer.accept("hello");

        java.util.function.Function<String,Integer> function=(a)->{
            return new Integer(a);
        };
        System.out.println(function.apply("213"));


        Supplier<Integer> supplier=()->{
            System.out.println("supplier");
            return 111;
        };
        System.out.println(supplier.get());

    }
    @FunctionalInterface
    interface  Function<T>{
        int multiplication(T a, T b);
        default void Sys(){
            System.out.println("default====");
        }


    }

    interface Compare<T> {
        int add(T a, T b);
        boolean equals(Object obj);
        public String toString();

        default int getBig() {
            return 12;
        }
        static int printO() {
            System.out.println("O");
            return 1;
        }
        static int print() {
            System.out.println("O");
            return 1;
        }
    }

    abstract class People {
       abstract int getNum();
    }


}
