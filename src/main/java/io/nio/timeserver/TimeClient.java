package io.nio.timeserver;

/**
 * @author: deadend
 * @date: 11:03 AM 1/10/17
 * @version: 1.0
 * @description:
 */


public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        new Thread(new TimeClientHandler("127.0.0.1", port)).start();
    }
}
