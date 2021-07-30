package java8.wxf.concurrent.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock: 读写锁，其读锁是共享锁，写锁是独占锁（ReentrantLock和synchronized都是独占锁）
 */
public class MyReentrantReadWriteLock {
   private Lock readLock = new ReentrantReadWriteLock().readLock();
   private Lock writeLock = new ReentrantReadWriteLock().writeLock();
}
