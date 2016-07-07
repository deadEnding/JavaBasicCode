package com.java.examples.deadlock;

/**
 * @author: java
 * @date: 8:02 PM 6/20/16
 * @version: 1.0
 * @description: 死锁代码示例
 * @refer: http://www.codeceo.com/article/java-thread-die-lock.html#0-tsina-1-9617-397232819ff9a47a7b7e80a40613cfe1
 */


public class DeadlockDemo {

    public static void main(String[] args) {
        Sync sy1 = new Sync(0);
        Sync sy2 = new Sync(1);
        sy1.start();
        sy2.start();
        try {
            sy1.join();
            sy2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
