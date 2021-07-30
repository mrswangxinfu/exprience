package java8.wxf.NIO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 *
 *  https://ifeve.com/java-nio-all/
 *  https://tech.meituan.com/2016/11/04/nio.html
 *
 * Java NIO(New IO)是一个可以替代标准Java IO API的IO API（从Java 1.4开始)，Java NIO提供了与标准IO不同的IO工作方式。
 *
 * Java NIO: Channels and Buffers（通道和缓冲区）
 *
 * 标准的IO基于字节流和字符流进行操作的，而NIO是基于通道（Channel）和缓冲区（Buffer）进行操作，数据总是从通道读取到缓冲区中，或者从缓冲区写入到通道中。
 *
 * Java NIO: Non-blocking IO（非阻塞IO）
 *
 * Java NIO可以让你非阻塞的使用IO，例如：当线程从通道读取数据到缓冲区时，线程还是可以进行其他事情。当数据被写入到缓冲区时，线程可以继续处理它。从缓冲区写入通道也类似。
 *
 * Java NIO: Selectors（选择器）
 *
 * Java NIO引入了选择器的概念，选择器用于监听多个通道的事件（比如：连接打开，数据到达）。因此，单个的线程可以监听多个数据通道。
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        client();
    }
    // channel入门
    public void demo() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\12.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(12);
        int bytesRead = fileChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println(" Read " + bytesRead);
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.compact();
//            buf.clear();
            bytesRead = fileChannel.read(buf);
        }
        fileChannel.close();
    }

    // NIO客户端
    public static void client() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open();
            // 设置为非阻塞
            socketChannel.configureBlocking(false);

            // 连接服务地址
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8012));

            if (socketChannel.finishConnect()) {
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + i++ + "-the information from client";
                    buffer.clear();
                    // 将数据放入缓冲区
                    buffer.put(info.getBytes());
                    // 翻转重置
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println(buffer);
                        // 将buffer数据写入channel
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
