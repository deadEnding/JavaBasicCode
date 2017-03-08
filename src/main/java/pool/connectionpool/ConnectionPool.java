package pool.connectionpool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author: deadend
 * @date: 2:10 PM 10/17/16
 * @version: 1.0
 * @description: 数据库连接池
 */


public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notify();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {      // 无超时，永久等待
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {               // 超时等待
                long future = System.currentTimeMillis() + mills;
                long remain = mills;

                while (pool.isEmpty() && remain > 0) {
                    pool.wait(remain);
                    remain = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty())
                    result = pool.removeFirst();

                return result;
            }
        }
    }
}
