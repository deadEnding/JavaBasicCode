package com.java.examples.synchronizer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author: deadend
 * @date: 9:12 PM 10/18/16
 * @version: 1.0
 * @description: 测试TwinsLock同步组件，为什么经常只是某两个线程获得锁？？
 */


public class TwinsLockTest {

    public void test() throws InterruptedException {
        final Lock lock = new TwinsLock();

        class Worker implements Runnable {

            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    lock.lock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Worker(), "Thread-" + i);
            thread.setDaemon(true);
            thread.start();
        }

        for (int i = 0; i < 30; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TwinsLockTest twinsLockTest = new TwinsLockTest();
        twinsLockTest.test();
    }
}
