package java8.wxf.gof.createType.prototype;


public class ConcretePrototype extends Prototype {

    private  String filed;

    public ConcretePrototype(String filed){
        this.filed=filed;
    }

    public void te1(){
        System.out.println("ConcretePrototype的te1()");
    }

    public void out(){
        System.out.println("子类的out()");
    }
    @Override
    Prototype myClone() {
        return new ConcretePrototype(filed);
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "filed='" + filed + '\'' +
                '}';
    }
}
