package io.nio.netty.http.request;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: deadend
 * @date: 9:59 PM 1/12/17
 * @version: 1.0
 * @description:
 */


public class HttpClient {


    public void connect(String host, int port) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder", new HttpResponseDecoder());
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder", new HttpRequestEncoder());
                            ch.pipeline().addLast("httpClientHandler", new HttpClientHandler());
                        }
                    });

            ChannelFuture cf = bs.connect(host, port).sync();
            cf.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 80;
        new HttpClient().connect("211.144.24.72", port);
    }
}
