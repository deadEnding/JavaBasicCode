package pool.connectionpool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author: deadend
 * @date: 2:24 PM 10/17/16
 * @version: 1.0
 * @description: 数据库驱动，动态代理模式
 */


public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return null;
        }
    }

    // 创建一个Connection的代理， 在commit时休眠100毫秒
    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class[] {Connection.class}, new ConnectionHandler());
    }
}
