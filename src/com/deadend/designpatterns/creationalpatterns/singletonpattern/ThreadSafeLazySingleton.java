package com.deadend.designpatterns.creationalpatterns.singletonpattern;

/**
 * @author: deadend
 * @date: 5:05 PM 6/15/16
 * @version: 1.0
 * @description: synchronized保证了线程安全，加锁影响了效率
 */


public class ThreadSafeLazySingleton {

    private static ThreadSafeLazySingleton instance;

    // 构造函数私有化
    private ThreadSafeLazySingleton() {};

    // 唯一的访问接口
    public static synchronized ThreadSafeLazySingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeLazySingleton();
        }
        return instance;
    }

    public void show() {
        System.out.println("Thread Safe Lazy Singleton");
    }
}
