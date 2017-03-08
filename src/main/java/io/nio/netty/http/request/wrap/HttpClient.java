package io.nio.netty.http.request.wrap;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.util.concurrent.Semaphore;

import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author: deadend
 * @date: 4:14 PM 1/13/17
 * @version: 1.0
 * @description:
 */


public class HttpClient {

    private String host;
    private int port;

    private EventLoopGroup group;
    private Bootstrap bootstrap;
    private Semaphore sem = new Semaphore(100);

    public HttpClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.group = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        this.bootstrap.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("httpDecoder", new HttpResponseDecoder());
                        ch.pipeline().addLast("httpAggregator", new HttpObjectAggregator(65536));
                        ch.pipeline().addLast("httpEncoder", new HttpRequestEncoder());
                        ch.pipeline().addLast("httpClientHandler", new HttpClientHandler(sem));
                    }
                });
    }

    public void get(String url) throws Exception {
        final HttpRequest request = new DefaultFullHttpRequest(HTTP_1_1, GET, new URI(url).toASCIIString());
        request.headers().set(HttpHeaderNames.CONNECTION, "close");
        request.headers().set(HttpHeaderNames.CONTENT_LENGTH, 0);
        sem.acquire();
        ChannelFuture future = bootstrap.connect(host, port);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (!future.isSuccess()) {
                    System.out.println("超时啦");
                    future.cause().printStackTrace();
                    return;
                }

                future.channel().writeAndFlush(request);
            }
        });
    }

    public void close() throws InterruptedException {
        group.shutdownGracefully().sync();
    }

    public static void main(String[] args) throws Exception {
//        HttpClient httpClient = new HttpClient("117.131.17.22", 8080);
        HttpClient httpClient = new HttpClient("127.0.0.1", 5000);
        for (int i = 0; i < 20000; i++) {
//            httpClient.get("/migudmp/phonetag/103/B3CCC246F434EBBC5BB2B6DDD1081FD5/18313591247");
            httpClient.get("/");
        }
//        httpClient.close();
    }
}
