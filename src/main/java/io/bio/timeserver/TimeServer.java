package io.bio.timeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: deadend
 * @date: 9:25 PM 1/9/17
 * @version: 1.0
 * @description:
 */


public class TimeServer {

    private int port;

    public TimeServer(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port: " + port);
            Socket socket = null;
            TimeServerHandlerExecutorPool pool = new TimeServerHandlerExecutorPool(50, 10000);

            while (true) {
                socket = server.accept();
                pool.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
            }
        }

        new TimeServer(port).run();
    }
}
