package pool.objectpool;

/**
 * @author: deadend
 * @date: 9:16 PM 8/29/16
 * @version: 1.0
 * @description: 创建对象的工厂接口
 */


public interface ObjectFactory<T> {

    /**
     * 创建并返回新对象
     * @return
     */
    public abstract T create();
}
