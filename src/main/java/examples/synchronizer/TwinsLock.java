package examples.synchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: deadend
 * @date: 8:46 PM 10/18/16
 * @version: 1.0
 * @description: 自定义共享锁、同步组件，同一时刻，只允许至多两个线程同时访问，超过两个线程访问将被阻塞
 */


public class TwinsLock implements Lock {

    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("Count must large than zero");
            }
            setState(count);
        }

        public int tryAcquireShared(int reduceCount) {
            for (;;) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(current, newCount)) {
                    return newCount;
                }
            }
        }

        public boolean tryReleaseShared(int returnCount) {
            for (;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    /* 以下方法未实现 */

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
