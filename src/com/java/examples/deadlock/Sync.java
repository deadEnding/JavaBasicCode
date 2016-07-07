package com.java.examples.deadlock;

/**
 * @author: java
 * @date: 8:38 PM 6/20/16
 * @version: 1.0
 * @description: 实现死锁的线程
 */


public class Sync extends Thread {

    private int flag;

    static Object x1 = new Object();
    static Object x2 = new Object();

    public Sync(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            if (flag % 2 == 0) {
                synchronized (x1) {
                    System.out.println(flag + " 得到x1的对象锁");
                    Thread.sleep(1000);
                    synchronized (x2) {
                        System.out.println(flag + " 得到x2的对象锁");
                    }
                    System.out.println(flag + " 释放了x2的对象锁");
                }
                System.out.println(flag + " 释放了x1的对象锁");
            } else {
                synchronized (x2) {
                    System.out.println(flag + " 得到x2的对象锁");
                    Thread.sleep(1000);
                    synchronized (x1) {
                        System.out.println(flag + " 得到x1的对象锁");
                    }
                    System.out.println(flag + " 释放了x1的对象锁");
                }
                System.out.println(flag + " 释放了x2的对象锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}