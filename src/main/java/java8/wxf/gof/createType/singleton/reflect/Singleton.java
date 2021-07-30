package java8.wxf.gof.createType.singleton.reflect;

/**
 * 使用反射破解和防御
 */
public class Singleton {
    private static final Singleton instance = new Singleton();
    // 构造器私有化
    private Singleton() {
        // 防御：再次创建时异常
//        if (null!=instance) {
//            throw new RuntimeException();
//        }
    }
    public static Singleton getInstance() {
        return instance;
    }
    public void getABC() {
    }
    public void getCBD() {}
}
