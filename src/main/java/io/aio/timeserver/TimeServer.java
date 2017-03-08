package io.aio.timeserver;

/**
 * @author: deadend
 * @date: 1:48 PM 1/10/17
 * @version: 1.0
 * @description:
 */


public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
