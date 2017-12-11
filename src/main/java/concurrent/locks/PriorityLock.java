package concurrent.locks;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: deadend
 * @date: 12:13 AM 02/04/2017
 */


public class PriorityLock extends ReentrantLock {

    class IllegalLockFetchException extends RuntimeException {}

    private static ThreadLocal<LinkedList<Integer>> gotPriorities = new ThreadLocal<LinkedList<Integer>>() {
        protected LinkedList<Integer> initialValue() {
            return new LinkedList<>();
        }
    };

    private int priority;

    public PriorityLock(int priority) {
        this.priority = priority;
    }

    public void lock() {
        LinkedList<Integer> got = gotPriorities.get();

        if (!got.isEmpty() && got.getLast() > priority) {
            throw new IllegalLockFetchException();
        }

        super.lock();
        got.addLast(priority);

        System.out.println(Thread.currentThread() + " Lock: " + priority);
    }

    public void unlock() {
        LinkedList<Integer> got = gotPriorities.get();
        if (got.isEmpty() || got.getLast() != priority) {
            throw new IllegalLockFetchException();
        }

        got.removeLast();
        super.unlock();

        System.out.println(Thread.currentThread() + "Unlock: " + priority);
    }

    public static void main(String[] args) throws InterruptedException {
        final PriorityLock lock1 = new PriorityLock(1);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock1.lock();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.unlock();
            }
        });
        t.start();
        lock1.lock();
        Thread.sleep(1000);
        lock1.unlock();
        t.join();
    }
}
