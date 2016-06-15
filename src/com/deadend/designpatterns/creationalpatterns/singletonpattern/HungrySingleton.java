package com.deadend.designpatterns.creationalpatterns.singletonpattern;

/**
 * @author: deadend
 * @date: 5:19 PM 6/15/16
 * @version: 1.0
 * @description: 类加载机制保证了线程安全，没有加锁，效率较高
 */


public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    // 构造函数私有化
    private HungrySingleton() {};

    // 唯一的访问接口
    public static HungrySingleton getInstance() {
        return instance;
    }

    public void show() {
        System.out.println("Hungray Singleton");
    }
}
