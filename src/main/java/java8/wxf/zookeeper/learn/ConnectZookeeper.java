package java8.wxf.zookeeper.learn;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ConnectZookeeper {
    public static void main(String[] args) {
        try {
            final CountDownLatch countDownLatch=new CountDownLatch(1);
            ZooKeeper zooKeeper=
                new ZooKeeper("192.168.0.50:2181,",
                        4000, new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {
                        if(Event.KeeperState.SyncConnected==event.getState()){
                            //如果收到了服务端的响应事件，连接成功
                            countDownLatch.countDown();
                        }
                    }
                });
            countDownLatch.await();
            //CONNECTED
            System.out.println("hello<>Zookeeper  "+ zooKeeper.getState());
            //添加节点
            zooKeeper.create("/hello","00000---zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (IOException | InterruptedException |KeeperException e) {
            e.printStackTrace();
        }
    }
}
