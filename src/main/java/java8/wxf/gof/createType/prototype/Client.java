package java8.wxf.gof.createType.prototype;

import java.util.Date;

public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype=new ConcretePrototype("hello");
        Prototype concretePrototype=prototype.myClone();

//        ConcretePrototype concretePrototype1=(ConcretePrototype) prototype.myClone();
// 浅克隆
//        System.out.println(concretePrototype.toString());
//        System.out.println(prototype.toString());
//        System.out.println(prototype==concretePrototype);

//        prototype.te1();
//        concretePrototype.te1();
//        concretePrototype1.te2();
//        concretePrototype1.out();
        // 深克隆
        DeepClone clone = new DeepClone();
        clone.setName("深克隆");
        clone.setBirthday(new Date());

        DeepClone deepClone = (DeepClone)clone.clone();

        System.out.println(clone);
        System.out.println(deepClone);
        System.out.println(clone.getBirthday()==deepClone.getBirthday());
    }
}
