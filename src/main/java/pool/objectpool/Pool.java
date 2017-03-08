package pool.objectpool;

/**
 * @author: deadend
 * @date: 8:54 PM 8/29/16
 * @version: 1.0
 * @description: 对象池接口
 */



public interface Pool<T> {

    /**
     * 返回对象池中的可用对象
     * @return 对象
     */
    T get();


    /**
     * 释放对象并放回对象池
     * @param t
     */
    void release(T t);


    /**
     * 关闭对象池，释放全部对象
     */
    void shutdown();


    public static interface Validator<T> {

        /**
         * 判断对象是否合法
         * @return 是否合法
         */
        public boolean isValid(T t);


        /**
         * 使对象非法，用于释放对象资源
         * @param t: 对象
         */
        public void invalidate(T t);
    }
}
