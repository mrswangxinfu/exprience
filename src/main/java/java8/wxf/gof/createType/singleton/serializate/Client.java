package java8.wxf.gof.createType.singleton.serializate;

import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton singleton1 = Singleton.getInstance();

        File tempFile = new File("D:/test1/jk.java");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile));
        oos.writeObject(singleton1);
        oos.close();
        ObjectInputStream ios = new ObjectInputStream(new FileInputStream(tempFile));
        Singleton singleton2 = (Singleton) ios.readObject();
        ios.close();
        System.out.println(singleton1 == singleton2);
    }
}
