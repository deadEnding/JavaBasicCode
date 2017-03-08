package designpatterns.old.creationalpatterns.singletonpattern;

/**
 * @author: java
 * @date: 4:47 PM 6/15/16
 * @version: 1.0
 * @description: 非线程安全，多线程同时进入时可能造成instance被多次实例化
 */


public class NotThreadSafeLzaySingleton {

    private static NotThreadSafeLzaySingleton instance;

    // 构造函数私有化
    private NotThreadSafeLzaySingleton() {};

    // 唯一的访问接口
    public static NotThreadSafeLzaySingleton getInstance() {
        if (instance == null) {
            instance = new NotThreadSafeLzaySingleton();
        }
        return instance;
    }

    public void show() {
        System.out.println("Not Thread Safe Lzay Singleton");
    }
}
