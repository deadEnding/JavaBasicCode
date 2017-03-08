package io.nio.netty.timeserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * @author: deadend
 * @date: 4:26 PM 1/10/17
 * @version: 1.0
 * @description:
 */


public class TimeServer {

    public void bind(int port) throws InterruptedException {
        // 配置服务的的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup worderGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sbs = new ServerBootstrap();
            sbs.group(bossGroup, worderGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler());
            // 绑定端口，同步等待成功
            ChannelFuture cf = sbs.bind(port).sync();

            // 等待服务器监听端口关闭
            cf.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            worderGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }
        }

        new TimeServer().bind(port);
    }
}
