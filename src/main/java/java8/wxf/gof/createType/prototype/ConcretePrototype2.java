package java8.wxf.gof.createType.prototype;


public class ConcretePrototype2 extends Prototype1 {

    private  String filed;

    public ConcretePrototype2(){}
    public ConcretePrototype2(String filed){
        this.filed=filed;
    }

    public void out(){
        System.out.println("子类的out()");
    }

    public void te1(){
        System.out.println("ConcretePrototype的te1()");
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "filed='" + filed + '\'' +
                '}';
    }
}
