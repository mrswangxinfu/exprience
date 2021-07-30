package java8.wxf.gof.createType.singleton;

/**
 * 单例模式
 * 使用枚举可以防止反射攻击，
 * 反射攻击：在其他实现中可以通过setAccessible()将私有构造函数的访问级别设置为public.
 */
public enum Singleton_enum {

//    INSTANCE;
//
//    private Singleton singleton;
//    Singleton_enum(){
////        singleton=new Singleton();
//    }
//    public void setSingleton(Singleton singleton){
//        this.singleton=singleton;
//    }
//
//    public Singleton getSingleton(){
//        return singleton;
//    }
//
//    //测试
//    public static void main(String[] args) {
//        Singleton_enum first=Singleton_enum.INSTANCE;
//
//        Singleton singleton=new Singleton();
//        first.setSingleton(singleton);
//        System.out.println(first.getSingleton());
//
//
//        Singleton_enum second=Singleton_enum.INSTANCE;
////        Singleton singleton1=new Singleton();
//        second.setSingleton(singleton1);
//        System.out.println(first.getSingleton());
//        System.out.println(second.getSingleton());
//        System.out.println(first.getSingleton()==second.getSingleton());
//    }
}
