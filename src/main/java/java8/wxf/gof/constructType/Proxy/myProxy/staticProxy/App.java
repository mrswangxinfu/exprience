package java8.wxf.gof.constructType.Proxy.myProxy.staticProxy;

import java8.wxf.gof.constructType.Proxy.myProxy.Target;

/**
 * 测试代理
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        StaticProxy proxy;
        proxy=new StaticProxy(new Target());
        proxy.save();
        System.out.println(Target.string);
//        System.out.println(Class.forName("java8.wxf.gof.constructType.Proxy.myProxy.staticProxy.StaticProxy"));
    }
}
