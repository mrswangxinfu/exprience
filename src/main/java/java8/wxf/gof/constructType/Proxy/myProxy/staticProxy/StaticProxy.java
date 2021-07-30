package java8.wxf.gof.constructType.Proxy.myProxy.staticProxy;


import java8.wxf.gof.constructType.Proxy.myProxy.IUser;

/**
 * 静态代理
 *
 * 静态代理总结:
 * 1.可以做到在不修改目标对象的功能前提下,对目标功能扩展.
 * 2.缺点:
 *
 * 因为代理对象需要与目标对象实现一样的接口,所以会有很多代理类,类太多.同时,一旦接口增加方法,目标对象与代理对象都要维护.
 *
 * 如何解决静态代理中的缺点呢?
 * 答案是可以使用动态代理方式
 */
public class StaticProxy implements IUser {

    IUser target;

    /**
     * @param target 代理目标
     */
    public StaticProxy(IUser target){
        this.target=target;
    }

    @Override
    public void save() {
        System.out.println("开始事务----");
        target.save();
        System.out.println("提交事务----");
    }

    @Override
    public void save(String args) {
        System.out.println("开始事务----");
        target.save(args);
        System.out.println("提交事务----");
    }
}
