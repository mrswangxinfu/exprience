package java8.wxf.gof.createType.singleton.serializate;

import java.io.Serializable;

/**
 * 使用序列化破解和防御
 */
public class Singleton implements Serializable {
    private static final Singleton instance = new Singleton();
    private static final long serialVersionUID = -7360680054279284924L;
    private Singleton() {

    }
    public static Singleton getInstance() {
        return instance;
    }
    // 防御：反序列化时，直接调用该方法返回该方法的返回值
    /**
     * 该方法通过{@link java.io.ObjectStreamClass}类的private ObjectStreamClass(final Class<?> cl)方法
     * 进行反射寻找name为readResolve的方法 ----meth = {@link Class}defCl.getDeclaredMethod(name, argTypes);
     * @return
     */
    private Object readResolve() {
        return instance;
    }
}
