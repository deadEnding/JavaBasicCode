package io.nio.netty.http.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: deadend
 * @date: 3:08 PM 1/12/17
 * @version: 1.0
 * @description:
 */


public class HttpFileServer {

    private static final String DEFAULT_URL = "/";

    public void run(final int port, final String url) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
//                            ch.pipeline().addLast("fileServerHandler", new OtherHandler(url));
                        }
                    });

            ChannelFuture future = sbs.bind("127.0.0.1", port).sync();
            System.out.println("HTTP file server startup, location: http://127.0.0.1:" + port + url);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        String url = DEFAULT_URL;
        if (args.length > 1) {
            url = args[1];
        }
        new HttpFileServer().run(port, url);
    }
}
