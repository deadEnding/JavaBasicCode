package pool.objectpool;

import java.util.concurrent.*;

/**
 * @author: deadend
 * @date: 9:37 PM 8/29/16
 * @version: 1.0
 * @description: 有限阻塞对象池
 */


public final class BoundedBlockingPool<T> extends AbstractPool<T> implements BlockingPool<T> {

    private int size;
    private BlockingQueue<T> objects;
    private Validator<T> validator;
    private ObjectFactory<T> objectFactory;
    private ExecutorService executor = Executors.newCachedThreadPool();
    private volatile boolean shutdownCalled;


    public BoundedBlockingPool(int size, Validator<T> validator, ObjectFactory<T> objectFactory) {
        super();
        this.size = size;
        this.validator = validator;
        this.objectFactory = objectFactory;
        initObjects();
    }


    @Override
    public T get() {
        if (!shutdownCalled) {
            T t = null;
            try {
                t = objects.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                return t;
            }
        }

        throw new IllegalStateException("Object pool is already shutdown");
    }


    @Override
    public T get(long timeout, TimeUnit unit) {
        if (!shutdownCalled) {
            T t = null;
            try {
                t = objects.poll(timeout, unit);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                return t;
            }
        }

        throw new IllegalStateException("Object pool is already shutdown");
    }


    @Override
    public void shutdown() {

    }


    @Override
    protected boolean isValid(T t) {
        return validator.isValid(t);
    }


    @Override
    protected void recycle(T t) {
        if (validator.isValid(t)) {
        }
    }


    @Override
    protected void handleInvalid(T t) {

    }


    private void initObjects() {
        objects = new LinkedBlockingQueue(size);
        for (int i = 0; i < size; i++) {
            objects.add(objectFactory.create());
        }
    }


    private class ObjectReturner implements Callable {

        private BlockingQueue<T> queue;
        private T t;


        public ObjectReturner(BlockingQueue<T> queue, T t) {
            this.queue = queue;
            this.t = t;
        }


        @Override
        public Void call() {
            while (true) {
                try {
                    queue.put(t);
                    break;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return null;
        }
    }
}
