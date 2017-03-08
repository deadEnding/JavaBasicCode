package pool.threadpool;

import java.util.concurrent.TimeUnit;

/**
 * @author: deadend
 * @date: 10:58 PM 10/17/16
 * @version: 1.0
 * @description: 测试线程池
 */

class Job implements Runnable {

    private String name;

    public Job(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " " + Thread.currentThread().getName());
    }
}


public class ThreadPoolTest {

    public static void main(String[] args) {
        DefaultThreadPool<Job> defaultThreadPool = new DefaultThreadPool<>(4);
        System.out.println(defaultThreadPool.getJobSize());
        defaultThreadPool.execute(new Job("test1"));
        defaultThreadPool.execute(new Job("test2"));
        defaultThreadPool.execute(new Job("test3"));
        defaultThreadPool.execute(new Job("test4"));
        defaultThreadPool.removeWorker(3);
        defaultThreadPool.execute(new Job("test5"));
        defaultThreadPool.execute(new Job("test6"));
    }
}
