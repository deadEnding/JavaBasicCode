package examples.threadlocal;

/**
 * @author: java
 * @date: 2:53 PM 6/22/16
 * @version: 1.0
 * @description: 基本类型的变量线程不共享，对象变量是否共享取决于是否引用同一对象，TreadLocal变量不共享
 * @refer: http://www.cnblogs.com/xudong-bupt/archive/2013/05/22/3087864.html
 *         http://www.codeceo.com/article/java-threadlocal-deep-learn.html#0-tsina-1-99438-397232819ff9a47a7b7e80a40613cfe1
 */


public class ThreadLocalDemo {

    private static ThreadLocal<String> data = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " [begin] data=" + data.get());
                data.set("t1");
                System.out.println("Thread " + Thread.currentThread().getName() + " [end] data=" + data.get());
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName() + " [begin] data=" + data.get());
                data.set("t2");
                System.out.println("Thread " + Thread.currentThread().getName() + " [begin] data=" + data.get());
            }
        });

        t0.start();
        try {
            t0.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("==== Thread-0 done ====");
        t1.start();
    }
}
