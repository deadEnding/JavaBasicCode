package io.bio.timeserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author: deadend
 * @date: 9:46 PM 1/9/17
 * @version: 1.0
 * @description:
 */


public class TimeClient {

    private String host;
    private int port;

    public TimeClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QUERY TIME ORDER");
            System.out.println("Send order to server succeed.");
            String resp = in.readLine();
            System.out.println("Now is : " + resp);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out == null) {
                out.close();
                out = null;
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
            }
        }

        new TimeClient("127.0.0.1", port).run();
    }
}
