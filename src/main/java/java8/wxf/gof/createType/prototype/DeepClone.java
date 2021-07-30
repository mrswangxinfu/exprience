package java8.wxf.gof.createType.prototype;

import lombok.Data;

import java.io.*;
import java.util.Date;

/**
 * 深度克隆：重写clone方法
 * 1、基于Cloneable：必须实现Cloneable方法，不然报错CloneNotSupportedException
 * 2、基于序列化
 */
@Data
public class DeepClone implements Cloneable, Serializable {


    private static final long serialVersionUID = 2204818054400883566L;
    String name;
    private Date birthday;
//     重写Object的clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepClone deepClone = (DeepClone) super.clone();
        deepClone.setBirthday((Date)birthday.clone());// 若没有这行代码就是浅克隆，即复制了引用类型的地址，有的话就是复制引用类型的数据
        return deepClone;
    }

    // 基于序列化的深克隆
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        DeepClone deepClone = null;
//        ByteArrayOutputStream byteArrayOutputStream = null;
//        ByteArrayInputStream byteArrayInputStream = null;
//        ObjectOutputStream objectOutputStream = null;
//        ObjectInputStream objectInputStream = null;
//        try {
//            byteArrayOutputStream = new ByteArrayOutputStream();
//            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
//            objectOutputStream.writeObject(this);
//            byte[] byteArray= byteArrayOutputStream.toByteArray();
//            byteArrayInputStream = new ByteArrayInputStream(byteArray);
//            objectInputStream = new ObjectInputStream(byteArrayInputStream);
//            deepClone = (DeepClone) objectInputStream.readObject();
//        }catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return deepClone;
//    }
}
