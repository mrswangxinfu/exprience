package java8.wxf.gof.createType.prototype;


public class ConcretePrototype1 extends Prototype {

    private  String filed;

    public ConcretePrototype1(String filed){
        this.filed=filed;
    }
    @Override
    Prototype myClone() {
        return new ConcretePrototype1(filed);
    }

    @Override
    public String toString() {
        return "ConcretePrototype{" +
                "filed='" + filed + '\'' +
                '}';
    }
}
