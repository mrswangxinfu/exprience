package java8.wxf.concurrent.AQS;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * BlockingQueue的实现类：ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue
 */
public class MyBlockingQueue {
    BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
    BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
    BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
}
