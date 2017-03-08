package pool.objectpool;

/**
 * @author: deadend
 * @date: 9:00 PM 8/29/16
 * @version: 1.0
 * @description: 对象池抽象类
 */


public abstract class AbstractPool<T> implements Pool<T> {

    @Override
    public final void release(T t) {
        if (isValid(t)) {
            recycle(t);
        } else {
            handleInvalid(t);
        }
    }


    /**
     * 判断对象是否可回收
     * @param t: 对象
     * @return 是否可回收
     */
    protected abstract boolean isValid(T t);


    /**
     * 回收对象，放回对象池
     * @param t: 对象
     */
    protected abstract void recycle(T t);


    /**
     * 处理无效对象
     * @param t: 对象
     */
    protected abstract void handleInvalid(T t);
}
