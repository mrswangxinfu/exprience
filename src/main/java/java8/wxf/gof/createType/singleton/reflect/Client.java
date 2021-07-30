package java8.wxf.gof.createType.singleton.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Client {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Singleton singleton1 = Singleton.getInstance();

        // TODO
        // XXX
        // FIXME
        // HACK
        Class<Singleton> clazz = Singleton.class;
        // 获取构造方法
        Constructor<Singleton> constructor = clazz.getDeclaredConstructor();
        // 破解： 修改构造方法的权限
        constructor.setAccessible(true);
        Singleton singleton2 = constructor.newInstance();
        System.out.println(singleton1==singleton2);
        Method[] methods = Singleton.class.getDeclaredMethods();
        System.out.println(Singleton.class.getName());
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}
