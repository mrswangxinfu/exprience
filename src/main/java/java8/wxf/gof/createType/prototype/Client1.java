package java8.wxf.gof.createType.prototype;


/**
 * 测试向上转型和向下转型
 */
public class Client1 extends DeepClone implements Cloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
//
//        Prototype1 prototype1=new Prototype1();
//        Prototype1 prototype11=new ConcretePrototype2();

        //向下转型之前一定要进行向上转型！！向下转型是建立在向上转型上
        //否则在转型时会出现ClassCastException（类型转换异常–运行时异常
       // ConcretePrototype2 prototype2=(ConcretePrototype2)prototype1;
       // prototype2.out();

//
//        ConcretePrototype2 prototype12=(ConcretePrototype2) prototype11;
//        prototype12.out();
    }


}
