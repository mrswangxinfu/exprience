package java8.wxf.gof.createType.factory;

/**
 * 简单工厂模式
 * 由另一个类（SingletonFactory）实现创建对象
 */
public class SingletonFactory {


    /**
     * 使用接口可以向上转型
     * @param type
     * @return
     */
    public Product createProduct(int type){

        if(type==1){
            return new ConcreteProduct1();
        }else if(type==2){
            return new ConcreteProduct2();
        }else
            return new ConcreteProduct();
    }
}
