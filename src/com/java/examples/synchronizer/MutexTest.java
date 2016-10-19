package com.java.examples.synchronizer;

import java.util.concurrent.locks.Lock;

/**
 * @author: deadend
 * @date: 10:38 AM 10/19/16
 * @version: 1.0
 * @description: 测试互斥锁
 */


public class MutexTest {

    private static int THREAD_NUM = 10;
    private static int count = 0;

    public static void test() throws InterruptedException {

        class Worker implements Runnable {

            private Lock lock;
            private int delta;

            public Worker(Lock lock, int delta) {
                this.lock = lock;
                this.delta = delta;
            }

            @Override
            public void run() {
                lock.lock();
                try {
                    for (int i = 0; i < 10000000; i++)
                        count += delta;
                } finally {
                    lock.unlock();
                }
            }
        }

        Mutex mutex = new Mutex();

        // 奇数线程减1，偶数线程加1
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(new Worker(mutex, i % 2 == 0 ? 1 : -1));
            threads[i].start();
        }

        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i].join();
        }

        System.out.println("Result: " + count);
    }


    public static void main(String[] args) throws InterruptedException {
        MutexTest.test();
    }
}
