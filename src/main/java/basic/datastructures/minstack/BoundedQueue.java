package basic.datastructures.minstack;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: deadend
 * @date: 9:00 PM 3/7/17
 * @version: 1.0
 * @description: 阻塞队列
 */


public class BoundedQueue<T> {

    private Object[] items;
    private int addIndex, removeIndex, count;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundedQueue(int size) {
        items = new Object[size];
    }

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex %= items.length] = t;
            if (++addIndex == items.length) {   // 循环数组
                addIndex = 0;
            }
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await();
            }
            Object x = items[removeIndex];
            if (++removeIndex == items.length) {
                removeIndex = 0;
            }
            --count;
            notFull.signal();
            return (T) x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final int count = 100;
        final BoundedQueue<Integer> queue = new BoundedQueue<>(10);
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    try {
                        System.out.println("Removed: " + queue.remove());
                    } catch (InterruptedException e) {
                        System.out.println("Interuppted");
                    }
                }
            }
        });
        consumer.start();

        for (int i = 0; i < count; i++) {
            queue.add(i);
            System.out.println("Add: " + i);
        }

        System.out.println("OK");
    }
}
