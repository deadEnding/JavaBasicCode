package io.nio.netty.http.request.ddemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;

/**
 * @author: deadend
 * @date: 10:10 AM 1/13/17
 * @version: 1.0
 * @description:
 */


public class HttpClient {

//    public void connect(String host, int port) {
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        try {
//            Bootstrap bs = new Bootstrap();
//            bs.group(group).channel(NioSocketChannel)
//                    .option(ChannelOption.SO_KEEPALIVE, true)
//                    .handler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new HttpRequestDecoder())
//                        }
//                    })
//        }
//    }
}
