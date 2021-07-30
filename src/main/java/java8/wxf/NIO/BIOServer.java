package java8.wxf.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket： 携带IP+port的网络进程
 *
 * socket由操作系统（OS）函数操作
 */
public class BIOServer {

    /**
     * BIO是阻塞IO，一旦连接上只能发送数据解阻塞后才能接受新的连接
     * 缺点：在单线程下无法处理并发
     * 多线程下可以解决并发（尽量避免多线程）
     * 为什么避免多线程？
     * 浪费CPU资源
     *
     * NIO是为了解决BIO的阻塞原因:
     * NIO逻辑：将连接上的客户端保存到集合，再将连接设置非阻塞，将read设置非阻塞，然后遍历集合判断（轮询）。
     * @param args
     * @throws IOException
     */
 public  static void main(String[] args) throws IOException {

     byte[] date=new byte[1024];
     ServerSocket server=new ServerSocket();
     //bind去调用操作系统内部的函数
     server.bind(new InetSocketAddress(9876));

     while (true) {
         System.out.println("等待连接---");
         Socket socket=server.accept();//阻塞---程序释放cpu资源
         System.out.println("连接成功---");
         System.out.println("address:" + socket.getRemoteSocketAddress() + ", " + socket.getInetAddress());
         System.out.println("等待数据---");
         socket.getInputStream().read(date);//阻塞---等待数据
         System.out.println("收到数据---");
         String content=new String(date);
         System.out.println("数据为：");
         System.out.print(content);
     }
 }
}
