package com.java.examples.connectionpool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: deadend
 * @date: 2:35 PM 10/17/16
 * @version: 1.0
 * @description: 测试数据库连接池的工作状况
 */


public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);   // 保证所有ConnectionRunner可以同时开始
    static CountDownLatch start = new CountDownLatch(1);   // main线程将会等待所有ConnectionRunner结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        // 线程数量，可以修改线程数量进行观察
        int threadCount = 40;
        end = new CountDownLatch(threadCount);
        int count = 20;

        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }

        start.countDown(); // 线程同时执行
        end.await();       // 等待全部ConnectionRunner执行结束

        System.out.println("Total invoke: " + (threadCount * count));
        System.out.println("Got connection: " + got);
        System.out.println("Not got connection: " + notGot);
    }

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            } catch (Exception e) {}

            while (count > 0) {
                try {
                    // 从线程中获取连接，如果1000ms内无法取到，就会返回null
                    // 分别统计连接获取的数量got和未获取到的数量notGot
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        notGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
