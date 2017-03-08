package pool.threadpool;

/**
 * @author: deadend
 * @date: 4:46 PM 10/17/16
 * @version: 1.0
 * @description: 线程池接口
 */


public interface ThreadPool<Job extends Runnable> {

    // 执行一个实现了Runnable接口的Job
    void execute(Job job);

    // 关闭线程池
    void shutdown();

    // 增加Worker线程
    void addWorker(int num);

    // 移除Worker线程
    void removeWorker(int num);

    // 得到正在等待执行的任务数量
    int getJobSize();
}
