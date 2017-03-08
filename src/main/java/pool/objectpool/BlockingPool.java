package pool.objectpool;

import java.util.concurrent.TimeUnit;

/**
 * @author: deadend
 * @date: 9:19 PM 8/29/16
 * @version: 1.0
 * @description: 阻塞对象池接口
 */


public interface BlockingPool<T> extends Pool<T> {

    /**
     * 返回对象池中的可用对象
     *
     * 此调用为阻塞调用，当没有对象可用时，客户端线程会一直等待直至对象可用
     * 此调用需实现公平算法来保证先来先服务（FCFS）
     *
     * 建议客户端支持响应终端。当客户端线程在阻塞过程中被中断时，此实现将线程
     * 的终端状态设置为 true 并返回 null。
     *
     * @return 对象
     */
    T get();


    /**
     * 返回对象池中的可用对象
     *
     * 此调用为阻塞调用，当没有对象可用时，客户端线程会一直等待直到对象可用或超时
     * 其他方面与 get() 方法一致
     *
     * @param time: 等待时间
     * @param unit: 等待时间的单位
     * @return 对象
     */
    T get(long time, TimeUnit unit);
}
