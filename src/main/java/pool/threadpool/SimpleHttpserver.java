package pool.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: deadend
 * @date: 11:16 AM 10/18/16
 * @version: 1.0
 * @description: 简易Web服务器
 */


public class SimpleHttpserver {

    // 线程池
    private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>(10);

    // 根路径
    private static String basePath;

    // 套接字
    private static ServerSocket serverSocket;

    // 端口
    private static int port = 8080;


    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpserver.port = port;
        } else {
            System.out.println("Invalid port: " + port);
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpserver.basePath = basePath;
        } else {
            System.out.println("Invalid basePath: " + basePath);
        }
    }

    // 启动
    public static void start() throws IOException {
        System.out.println("Starting simple http server ...");
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            // 接受客户端Socket，创建一个HttpRequestHandler放入线程池运行
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }


    static class HttpRequestHandler implements Runnable {

        Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            String header = "";
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());

                header = reader.readLine();
                if (header == null) {
                    out.println("HTTP/1.1 400");
                    out.println("");
                } else {
                    // 由相对路径计算出绝对路径
                    String filePath = basePath + header.split(" ")[1];

                    // 如果请求资源的后缀为jpg或ico，则读取资源并输出
                    if (filePath.endsWith(".jpg") || filePath.endsWith(".ico")) {
                        in = new FileInputStream(filePath);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        int i = 0;
                        while ((i = in.read()) != -1) {
                            baos.write(i);
                        }

                        byte[] array = baos.toByteArray();
                        out.println("HTTP/1.1 200 OK");
                        out.println("Server: Molly");
                        out.println("Content-Type: image/jpeg");
                        out.println("Content-Length: " + array.length);
                        out.println("");
                        socket.getOutputStream().write(array, 0, array.length);
                    } else {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                        out = new PrintWriter(socket.getOutputStream());
                        out.println("HTTP/1.1 200 OK");
                        out.println("Server: Molly");
                        out.println("Content-Type: text/html;charset=UTF-8");
                        out.println("");
                        while ((line = br.readLine()) != null) {
                            out.println(line);
                        }
                    }
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }
        }

        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception e) {}
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SimpleHttpserver simpleHttpserver = new SimpleHttpserver();
        simpleHttpserver.setBasePath("/tmp/test/");
        simpleHttpserver.start();
    }
}
