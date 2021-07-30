package java8.wxf.gof.constructType.Proxy.myProxy;
/**
 * 被代理的对象(目标对象)
 */
public class Target implements IUser{
    public final static String string="***'''" ;
    @Override
    public void save() {
        System.out.println("----User保存了----");
    }

    @Override
    public void save(String args) {
        System.out.println("----User保存了----");
        System.out.println("参数为："+args);
    }
}
