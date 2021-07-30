package java8.wxf.gof.constructType.Proxy.myProxy.dynamicProxy.cglibProxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class App {
    public static void main(String[] args) {
       final CglibTarget cglibTarget = new CglibTarget();
//         CglibTarget  proxy= (CglibTarget)new CglibProxyFactory(new CglibTarget()).getProxyInstance();
//         proxy.say("123");

        CglibTarget proxy2 = (CglibTarget) Enhancer.create(cglibTarget.getClass(), new MethodInterceptor() {

             public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//                 System.out.println(args[0]);
                 if (null==args[0]) {
                     method.invoke(new CglibTarget(), "override Enhancer");
                 }
                 args[0] = "notName"; // 若方法被final或者static修饰后不能被拦截处理
                 return method.invoke(cglibTarget, args);
             }
        });
        proxy2.say("name");
    }
}
