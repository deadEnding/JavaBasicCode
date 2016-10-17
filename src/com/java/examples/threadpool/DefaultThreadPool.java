package com.java.examples.threadpool;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: deadend
 * @date: 4:49 PM 10/17/16
 * @version: 1.0
 * @description: 线程池接口的默认实现，线程池的本质是使用一个线程安全的工作队列连接Worker线程和客户端线程
 */


public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 最小线程数
    private static final int MIN_WORKER_NUMBERS = 1;

    // 默认线程数
    private static final int DEFAULT_WORKDER_NUMS = 5;

    // 最大线程数
    private static final int MAX_WORKER_NUMBERS = 10;

    // 任务列表
    private final LinkedList<Job> jobs = new LinkedList<>();

    // Worker列表
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    // Worker数量
    private int workerNum = DEFAULT_WORKDER_NUMS;

    // 线程编号
    private AtomicLong threadId = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(workerNum);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : num < MIN_WORKER_NUMBERS ?
                MIN_WORKER_NUMBERS : num;
        initializeWorkers(workerNum);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadId.incrementAndGet());
            thread.start();
        }
    }

    @Override
    public void execute(Job job) {
        if (job != null) {
            // 添加Job，通知Worker
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorker(int num) {
        synchronized (jobs) {
            // 限制新增的Worker数量不能超过最大值
            if (num + workerNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - workerNum;
            }
            initializeWorkers(num);
            workerNum += num;
        }
    }

    @Override
    public void removeWorker(int num) {
        synchronized (jobs) {
            if (num >= workerNum) {
                throw new IllegalArgumentException("beyond workNum");
            }

            // 按照给定的数量停止Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(0);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            workerNum -= count;
        }
    }

    @Override
    public int getJobSize() {
        return workerNum;
    }


    class Worker implements Runnable {
        // 是否运行
        private volatile boolean running = true;

        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.removeFirst();
                }

                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}
