package java8.wxf.myLearnJava8;

//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import java8.wxf.entity.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional<T>:
 *
 */
public class OptionalLearn {

    @Test
    public  void test1() throws Throwable {
        Optional.ofNullable(null).orElseThrow(()->new Throwable("123132") );

        Null<String> stringNull=new Null<>();
        System.out.println(stringNull.getOrNull(null)) ;
//        LambdaQueryWrapper<Person> wrapper=new LambdaQueryWrapper<>();
//        wrapper.eq(Person::getAge,"20");
        Person person=new Person();
        System.out.println( person.get(null)+"");
    }

    public final class Null<J>{
        public J value;
        public  <J>  Null<J> getOrNull(J value){
            return (Null<J>)value;
//            return this.value == null ? null : (Null<J>) value;
        }
    }
}
