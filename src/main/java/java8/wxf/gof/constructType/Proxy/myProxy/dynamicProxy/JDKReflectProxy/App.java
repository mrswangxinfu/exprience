package java8.wxf.gof.constructType.Proxy.myProxy.dynamicProxy.JDKReflectProxy;

import java8.wxf.gof.constructType.Proxy.myProxy.IUser;
import java8.wxf.gof.constructType.Proxy.myProxy.Target;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 创建目标对象
         */
        IUser target=new Target();
        System.out.println(target.getClass().getClassLoader());
        System.out.println(target.getClass().getInterfaces());
        System.out.println(target.getClass());
        /**创建代理**/
        IUser proxy=(IUser) new ProxyFactory(target).getProxyInstance();
//        System.out.println(proxy.getClass());
        /**
         * 执行目标对象方法
         */
        proxy.save("参数");

    }
}
