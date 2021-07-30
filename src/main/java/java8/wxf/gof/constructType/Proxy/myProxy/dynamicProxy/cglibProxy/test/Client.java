package java8.wxf.gof.constructType.Proxy.myProxy.dynamicProxy.cglibProxy.test;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {
//    public static void main(String[] args) {
//        Producer producer = new Producer();
//        Producer proxy = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
//            @Override
//            public Object intercept(Object o, Method method, Object[] argList, MethodProxy methodProxy) throws Throwable {
////                method.invoke(producer);
//                System.out.println(methodProxy);
//                return method.invoke(producer,argList);
//            }
//        });
//        proxy.sale("12","55");
//    }

    public static void main(String[] args) {

        IPayment payment = (IPayment) Proxy.newProxyInstance(IPayment.class.getClassLoader(), new Class[]{IPayment.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return IPayment.class.getName() + "接口方法：" + method.getName() + "  支付现金：" + args[0];
                    }
                });

        System.out.println(payment.pay(89d));
    }
}
